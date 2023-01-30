import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class imagenesEntidades {
    private static Properties config;

    // impide que se creen instancias de la clase
    private imagenesEntidades() {
        throw new AssertionError();
    }

    // minimiza el fondo transparente de las imágenes contenidas en la carpeta origen y las escribe,
    // espejadas o no, en la carpeta destino
    public static void convert(String sourcePath, String outputFolder) throws IOException {
        boolean espejar = config.getProperty("espejar").equalsIgnoreCase("true");
        File folder = new File(sourcePath);

        // dimensiones minimizadas de cada imagen particular en la misma carpeta
        Map<String, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> imagenDimensionMap = new HashMap<>();

        // dimensiones minimizadas máximas entre todas las imágenes minimizadas que están en la misma carpeta
        int minWidth = Integer.MAX_VALUE, maxWidth = 0, minHeight = Integer.MAX_VALUE, maxHeight = 0;

        // recorrer todos los archivos de la carpeta para encotrar las dimensiones de las imágenes
        for (File file:folder.listFiles()) {
            // si es una carpeta resolver recursivamente
            if (file.isDirectory()) {
                convert(file.getPath(), outputFolder + "/" + file.getName());
            } else {
                String filename = file.getName();
                String filepath = file.getPath();

                // si la extensión no es válida continuar la iteración
                if (!extensionValida(filenameToExtension(filename)))
                    continue;

                // leer la imágen
                BufferedImage original = ImageIO.read(new File(filepath));
                int width = original.getWidth();
                int height = original.getHeight();

                // guardará las dimensiones de la imagen
                int x1 = Integer.MAX_VALUE, x2 = 0, y1 = Integer.MAX_VALUE, y2 = 0;

                // minimizar el tamaño de la imagen quitando pixeles transparentes
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {

                        // si no es un pixel transparente, lo tiene en cuenta
                        if (original.getRGB(i, j) != 0) {
                            x1 = Math.min(x1, i);
                            x2 = Math.max(x2, i);
                            y1 = Math.min(y1, j);
                            y2 = Math.max(y2, j);
                        }
                    }
                }

                Pair<Integer, Integer> x = new Pair<>(x1, x2);
                Pair<Integer, Integer> y = new Pair<>(y1, y2);

                // guardar las dimensiones de la imagen minimizada
                imagenDimensionMap.put(
                        filepath,
                        new Pair<>(x, y)
                );

                // comparar las dimensiones de la imagen con el resto de imágenes en la carpeta
                minWidth = Math.min(minWidth, x1);
                maxWidth = Math.max(maxWidth, x2);
                minHeight = Math.min(minHeight, y1);
                maxHeight = Math.max(maxHeight, y2);
            }
        }

        // recorrer todos lo archivos de la carpeta para escribir cada imagen en particular
        for (File file:folder.listFiles()) {
            String filename = file.getName();
            String filepath = file.getPath();

            // si la extensión no es válida continuar la iteración
            String extension = filenameToExtension(filename);
            if (!extensionValida(extension))
                continue;

            // lee la imagen original
            BufferedImage original = ImageIO.read(new File(filepath));

            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> xy = imagenDimensionMap.get(filepath);
            Pair<Integer, Integer> x = xy.getKey();
            Pair<Integer, Integer> y = xy.getValue();

            // crea una imagen nueva con las dimensiones minimizadas máximas común a todas las imágenes de la carpeta
            BufferedImage transformed = new BufferedImage(
                    maxWidth - minWidth,
                    maxHeight - minHeight,
                    BufferedImage.TYPE_INT_ARGB
            );

            // dimensiones parituculares de la imagen
            int width = x.getValue()-x.getKey();
            int height = y.getValue()-y.getKey();

            Graphics g = transformed.getGraphics();

            g.drawImage(
                original,
                !espejar?0:transformed.getWidth()-width,
                transformed.getHeight() - height,
                width+(!espejar?0:transformed.getWidth()-width),
                transformed.getHeight(),
                !espejar? x.getKey():x.getValue(),
                y.getKey(),
                !espejar? x.getValue():x.getKey(),
                y.getValue(),
                null
            );

            g.dispose();

            // el nombre de la imagen debe tener el formato: entidad_accion_número
            String nombreSinExtension = filename.replaceFirst("\\.[^.]*$", "");
            String regex = ".*_(\\w+)_([0-9]+)$";
            Matcher matcher = Pattern.compile(regex).matcher(nombreSinExtension);

            if(matcher.matches()) {
                // escribir la imagen transformada
                String tipo = matcher.group(1);
                String numero = matcher.group(2);
                String reemplazo = config.getProperty("reemplazar_" + tipo);

                // permite reemplazar la acción de la imagen original por otra leída por configuración
                if(reemplazo != null && !reemplazo.equals(""))
                    tipo = reemplazo;

                Files.createDirectories(Paths.get(outputFolder + "/" + tipo));
                File outputfile = new File(outputFolder + "/" + tipo + "/" + Integer.parseInt(numero)+ "." + extension);

                // mostrar por pantalla la imagen que se está escribiendo
                System.out.println(outputfile.getPath());

                // redimensionar la imagen
                int newHeight = Integer.parseInt(config.getProperty("height"));
                int newWidth = (newHeight * transformed.getWidth()) / transformed.getHeight();

                BufferedImage redimensionada = transformed;
                if (newHeight > 0) {
                    redimensionada = new BufferedImage(
                        newWidth,
                        newHeight,
                        BufferedImage.TYPE_INT_ARGB
                    );

                    Graphics g2 = redimensionada.getGraphics();
                    g2.drawImage(transformed.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                    g2.dispose();
                }

                ImageIO.write(redimensionada, extension, outputfile);
            }
            else
                System.err.println("El archivo " + filepath + "no cumple con el formato");
        }
    }

    // obtener la extensión de un archivo
    static String filenameToExtension(String filename) {
        String[] aux = filename.split("[.]");
        return aux.length > 0? aux[aux.length-1]:"";
    }

    static boolean extensionValida(String extension) {
        return (extension.equals("png")
            || extension.equals("jpg")
            || extension.equals("jpeg")
            || extension.equals("gif")
            || extension.equals("bmp"));
    }

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                config = new Properties();
                config.load(new FileInputStream(args[0]));
                convert(args[1], args[2]);
                System.out.println("\nlas imágenes se convirtieron correctamente!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("error! uso: java -jar achicarImagenes.jar <config.properties> <source_path> <output_path>");
        }
    }

    // clase auxiliar para almacenar las dimensiones de las imágenes
    public static class Pair<K, V> {
        protected final K key;
        protected final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }

    }
}