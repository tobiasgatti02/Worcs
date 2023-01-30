import java.io.File;
import java.util.Scanner;

public class RotarArchivosImagenes {
    /**
     *  Uso: RotarArchivosImagenes carpeta offset
        Rota de manera circular los archivos de la carpeta especificada, con el offset especificado
     */
    public static void main(String[] args) {
        final String extension = ".png";
        if(args.length != 2)
            System.err.println("Cantidad de argumentos inválida");
        else
        {
            File carpeta = new File(args[0]);
            System.out.println("ALERTA! Esto rotará las imágenes en " + carpeta.getPath());
            System.out.println("Seguro? (s/n)");
            if(new Scanner(System.in).next().toLowerCase().charAt(0) == 's') {
                if (!carpeta.isDirectory())
                    System.err.println("La carpeta no existe");
                else {
                    int offset = Integer.parseInt(args[1]);
                    int cantArchivos = 0;
                    for (File arch : carpeta.listFiles()) {
                        cantArchivos++;
                    }
                    for (int i = 0; i < cantArchivos; i++) {
                        final File file = new File(carpeta + "/" + i + extension);
                        if (!file.renameTo(new File(carpeta + "/" + i + "x" + extension))) {
                            System.err.println("Error al renombrar " + carpeta + "\\" + i + extension);
                        }
                    }
                    for (int i = 0; i < cantArchivos; i++) {
                        final int nuevoNum = (i + offset + cantArchivos) % cantArchivos;
                        if(!new File(carpeta + "/" + i + "x" + extension).renameTo(new File(carpeta + "/" + nuevoNum + extension)))
                            System.err.println("Error al renombrar " + (carpeta + "/" + i + "x" + extension));
                    }

                }
            }
        }
    }
}
