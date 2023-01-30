import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Scanner;

public class imagenesCartas {
    private static Image imgPlantilla;
    private static Image superposicion;

    private static Properties config;
    private static Properties costos;

    /*
        Crea las imágenes para las gráficas de las carrtas
        Uso: imagenesCartas.imagenesCartas <archivoConfig> <carpetaOrigen> <carpetaDestino>

        De acuerdo a la configuracion de archivoConfig, recorre la carpeta de origen, y crea las imagenes de salida en carpetaDestino.
        Las imágenes se basan en una plantilla, a la que se superpone cada imágen de origen(ícono), además del texto con el costo de las monedas.
        El costo de las monedas es leido del archivo de configuracion archivoCostos (especificado en el archivo de configuracion). Para cada imagen, se busca una propiedad con el nombre de PrecioCarta<nombreSinExtension>

        Además, se crea la imagen para cuando la carta está seleccionada, superponiendo una imágen con transparencia.
     */
    public static void main(String[] args) {
        boolean continuar;
        File plantilla;
        File carpetaOrigen;
        File carpetaDestino;
        char aux;
        if(args.length != 3)
            System.err.println("Número de argumentos inválido");
        else{
            try {
                config = new Properties();
                config.load(new FileInputStream(args[0]));

                costos = new Properties();
                costos.load(new FileInputStream(config.getProperty("archivoCostos")));

                plantilla = new File(config.getProperty("plantilla"));
                carpetaOrigen = new File(args[1]);
                carpetaDestino = new File(args[2]);

                if (!plantilla.isFile())
                    System.err.println("La plantilla no existe");
                else if (!carpetaOrigen.isDirectory())
                    System.err.println("La carpeta de origen no existe");
                else {
                    if (!config.getProperty("omitirWarning").equalsIgnoreCase("true") && carpetaDestino.exists()) {
                        System.out.print("La carpeta de destino ya existe. Está seguro de que desea continuar? (s/n):");
                        aux = new Scanner(System.in).next().charAt(0);
                        continuar = aux == 's' || aux == 'S';
                    } else
                        continuar = true;

                    if (continuar)
                        procesar(plantilla, carpetaOrigen, carpetaDestino);
                }
            } catch (FileNotFoundException e) {
                System.err.println("El archivo de propiedades o el archivo de costos no existen");
            } catch (IOException e) {
                System.err.println("Error al leer archivo de propiedades o costos");
            }
        }

    }

    private static void procesar(File plantilla, File carpetaOrigen, File carpetaDestino){
        boolean ok = true;
        try {
            imgPlantilla = ImageIO.read(plantilla);
        } catch (IOException e) {
            System.err.println("Error al leer plantilla");
            ok = false;
        }
        String superposicionSeleccionada = config.getProperty("superposicionSeleccionada");
        if(!superposicionSeleccionada.trim().equals("")) {
            try {
                superposicion = ImageIO.read(new File(superposicionSeleccionada));
            } catch (IOException e) {
                System.err.println("Error al leer superpoición");
                ok = false;
            }
        }
        if(ok)
            procesarCarpetas(carpetaOrigen, carpetaDestino);
    }

    private static void procesarCarpetas(File carpetaOrigen, File carpetaDestino) {
        procesarArchivos(carpetaOrigen, carpetaDestino);
        for (File subcarpeta : carpetaOrigen.listFiles(File::isDirectory)) {
            procesarCarpetas(subcarpeta, new File(carpetaDestino.getAbsolutePath() + "/" + subcarpeta.getName()));
        }
    }

    private static void procesarArchivos(File carpetaOrigen, File carpetaDestino) {
        try {
            Files.createDirectories(carpetaDestino.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File archivo : carpetaOrigen.listFiles(File::isFile)) {
            if (!archivo.getName().matches(".*\\." +
                    "(png)|" +
                    "(jpg)|" + "(jpeg)|" +
                    "(gif)|" +
                    "(webp)|" +
                    "(bmp)" +
                    "$")) {
                System.out.println("Advertencia: Archivo " + archivo.getPath() + " con extensión no admitida");
            }else{
                procesarImagen(archivo, carpetaDestino);
            }
        }
    }

    private static void procesarImagen(File archivoOrigen, File carpetaDestino){
        Image imgOrigen;
        try {
            imgOrigen = ImageIO.read(archivoOrigen);
            String costo = obtenerCosto(archivoOrigen);
            if(costo != null && !costo.equals("")) {
                BufferedImage imgDestino = new BufferedImage(
                        imgPlantilla.getWidth(null),
                        imgPlantilla.getHeight(null),
                        BufferedImage.TYPE_INT_ARGB);

                Graphics2D g = imgDestino.createGraphics();
                g.setColor(obtenerColorFondo());
                g.fillRect(0,0, imgDestino.getWidth(), imgDestino.getHeight());

                g.drawImage(
                        imgOrigen,
                        leerConfigInt("xIcono"),
                        leerConfigInt("yIcono"),
                        leerConfigInt("anchoIcono"),
                        leerConfigInt("altoIcono"),
                        null
                );

                g.drawImage(imgPlantilla, 0, 0, null);

                Font f = new Font(
                        config.getProperty("fuenteCosto"),
                        obtenerEstiloCosto(),
                        leerConfigInt("tamanoCosto")
                );

                FontMetrics m = g.getFontMetrics(f);
                g.setFont(f);


                int xCosto = leerConfigInt("xCosto") + m.stringWidth(costo) / 2;
                int yCosto = leerConfigInt("yCosto");

                g.setColor(obtenerColorCosto());
                g.drawString(costo,
                        xCosto,
                        yCosto);

                String nombreArchivo = quitarExtension(archivoOrigen.getName())
                        +"."
                        +config.getProperty("formatoSalida");
                String nombreArchivoSeleccionada = quitarExtension(archivoOrigen.getName())
                        +config.getProperty("sufijoSeleccionada")
                        +"."
                        +config.getProperty("formatoSalida");

                ImageIO.write(imgDestino, config.getProperty("formatoSalida"), new File(carpetaDestino + "/" + nombreArchivo));
                g.dispose();

                if(superposicion != null)
                    ImageIO.write(crearImagenSeleccionada(imgDestino), config.getProperty("formatoSalida"), new File(carpetaDestino + "/" + nombreArchivoSeleccionada));
            }
            else
                System.err.println("Error: no se pudo obtener el costo de " + archivoOrigen.getName());
        } catch (IOException e) {
            System.err.println("Error: No se pudo leer la imagen " + archivoOrigen.getPath());
        }
    }

    private static Color obtenerColorFondo() {
        String s = config.getProperty("colorFondo");
        String[] componentes = s.split(",");
        return new Color(Integer.parseInt(componentes[0]),
                Integer.parseInt(componentes[1]),
                Integer.parseInt(componentes[2]));

    }

    private static BufferedImage crearImagenSeleccionada(Image imgNormal) {
        int ancho = imgNormal.getWidth(null);
        int alto = imgNormal.getHeight(null);
        BufferedImage imgSeleccionada = new BufferedImage(
                ancho,
                alto,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imgSeleccionada.createGraphics();
        g.drawImage(imgNormal,0,0,ancho, alto,null);
        g.drawImage(superposicion,0,0,ancho, alto,null);
        g.dispose();
        return imgSeleccionada;
    }

    private static String obtenerCosto(File archivoOrigen) {
        return costos.getProperty("precioCarta" + quitarExtension(archivoOrigen.getName()));
    }

    private static String quitarExtension(String s){
        return s.replaceFirst("\\.[^.]+$", "");
    }

    private static Color obtenerColorCosto(){
        String s = config.getProperty("colorCosto");
        String[] componentes = s.split(",");
        return new Color(Integer.parseInt(componentes[0]),
                Integer.parseInt(componentes[1]),
                Integer.parseInt(componentes[2]));
    }

    private static int leerConfigInt(String clave){
        return Integer.parseInt(config.getProperty(clave));
    }

    public static int obtenerEstiloCosto() {
        String estilo = config.getProperty("estiloCosto");
        switch (estilo.toUpperCase()){
            case "PLAIN" -> {
                return Font.PLAIN;
            }
            case "BOLD" -> {
                return Font.BOLD;
            }
            case "ITALIC" -> {
                return Font.ITALIC;
            }
            case "BOLDITALIC"->{
                return Font.BOLD | Font.ITALIC;
            }
            default -> {
                System.out.println("El estilo de icono no es válido (PLAIN, BOLD, ITALIC, BOLDITALIC). Usando PLAIN");
                return Font.PLAIN;
            }
        }
    }
}
