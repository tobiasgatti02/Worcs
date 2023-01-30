import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class AnimarFondo {

    public static final GraphicsConfiguration conf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.err.println("Uso: AnimarFondo <rutaConfig>");
        }
        else {
            List<ComponenteImagen> componentes;
            Properties properties = new Properties();
            properties.load(new FileInputStream(args[0]));
            String rutaSalida = properties.getProperty("rutaSalida");
            File salida = new File(rutaSalida);
            if(salida.isDirectory()){
                System.out.println("La carpeta de salida ya existe (" + rutaSalida + ")");
                System.out.print("Está seguro de que desea continuar? (s/n): ");
                if(new Scanner(System.in).next().toLowerCase().charAt(0) != 's') {
                    System.exit(0);
                }
                System.out.println();
                eliminarCarpeta(salida);
            }
            int anchoSalida = Integer.parseInt(properties.getProperty("anchoSalida"));
            int altoSalida = Integer.parseInt(properties.getProperty("altoSalida"));
            int i = 1;
            String prop;
            componentes = new LinkedList<>();
            do {
                prop = properties.getProperty("imagen" + i);
                if (prop != null && !prop.equals("")) {
                    String[] compsImg = prop.split(";");
                    ComponenteImagen aux = new ComponenteImagen(
                            compsImg[0],
                            Integer.parseInt(compsImg[1]),
                            new Point(Integer.parseInt(compsImg[2]), Integer.parseInt(compsImg[3])),
                            new Dimension(Integer.parseInt(compsImg[4]), Integer.parseInt(compsImg[5])),
                            Integer.parseInt(compsImg[6])
                    );
                    componentes.add(aux);
                }
                i++;
            } while (prop != null);

            Files.createDirectory(Path.of(rutaSalida));
            int cantRepeticiones = Integer.parseInt(properties.getProperty("cantImagenesSalida"));
            componentes.sort(Comparator.naturalOrder());

            for (int rep = 0; rep < cantRepeticiones; rep++) {
                BufferedImage imgSalida = conf.createCompatibleImage(anchoSalida, altoSalida, Transparency.TRANSLUCENT);
                Graphics2D graphics = imgSalida.createGraphics();
                for (ComponenteImagen comp : componentes) {
                    graphics.drawImage(comp.getImagen(rep * 100 / cantRepeticiones), comp.getPos().x, comp.getPos().y, comp.getTamano().width, comp.getTamano().height, null);
                }
                graphics.dispose();
                guardarImagen(imgSalida, rutaSalida, rep);
            }
        }
    }

    private static void eliminarCarpeta(File carpeta) {
        for (File file : carpeta.listFiles()) {
            if(file.isDirectory())
                eliminarCarpeta(carpeta);
            else
                if(!file.delete())
                    throw new RuntimeException("Error al borrar archivo " + file.getPath());
        }
        if(!carpeta.delete())
            throw new RuntimeException("Error al borrar carpeta " + carpeta.getPath());
    }

    private static void guardarImagen(BufferedImage img, String rutaSalida, int rep) {
        try {
            ImageIO.write(img, "png", new File(rutaSalida + "/" + rep + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ComponenteImagen implements Comparable<ComponenteImagen>{
        private final int ordenZ;
        private final Point pos;
        private final Dimension tamano;
        private final String ruta;
        private final int cantImagenes;

        public ComponenteImagen(String ruta, int cantImagenes, Point pos, Dimension tamano, int ordenZ){
            this.ruta = ruta;
            this.cantImagenes = cantImagenes;
            this.pos = pos;
            this.tamano = tamano;
            this.ordenZ = ordenZ;
        }

        public int getOrdenZ() {
            return ordenZ;
        }

        public Point getPos() {
            return pos;
        }

        public Dimension getTamano() {
            return tamano;
        }

        public Image getImagen(int porcentaje) {
            int indice;
            String ruta;
            if(porcentaje < 100) {
                indice = (int)(porcentaje * cantImagenes / 100.0);
            }
            else {
                indice = cantImagenes - 1;
            }
            ruta = this.ruta + "/" + indice + ".png";
            try {
                System.out.println(ruta);
                BufferedImage img = ImageIO.read(new File(ruta));
                BufferedImage imgReescalada = conf.createCompatibleImage(tamano.width, tamano.height, Transparency.TRANSLUCENT);
                Graphics2D gfx = imgReescalada.createGraphics();
                gfx.drawImage(img, 0,0, tamano.width, tamano.height, null);
                gfx.dispose();
                return imgReescalada;
            } catch (IOException e) {
                throw new RuntimeException("Error al leer imagen "+ruta);
            }
        }

        @Override
        public int compareTo(ComponenteImagen c) {
            return Objects.compare(ordenZ,c.getOrdenZ(), Comparator.naturalOrder());
        }
    }
}
