package util.opcionesUsuario;

import java.io.*;
import java.util.Properties;

public class AdministradorOpciones {
    public static AdministradorOpciones instance;
    private final Properties opciones;
    private static final String rutaOpciones="./opciones.properties";
    protected AdministradorOpciones(){
        this.opciones = new Properties();
        File fOpciones = new File(rutaOpciones);
        if(fOpciones.isFile())
            try {
                opciones.load(new FileInputStream(fOpciones));
            } catch (IOException e) {
                crearOpciones();
            }
        else
            crearOpciones();
    }

    public static AdministradorOpciones getInstance() {
        if (instance == null) {
            instance = new AdministradorOpciones();
        }
        return instance;
    }

    public double getOpcion(OpcionesDouble o){
        String opcion = opciones.getProperty(o.getClave());
        double toReturn = 0;
        boolean error = false;

        if (opcion != null) {
            try {
                toReturn = Double.parseDouble(opcion);
            } catch (NumberFormatException e) {
                error = true;
            }
        } else
            error = true;

        if(error){
            System.err.println("Error al leer parámetro de opciones: " + o.getClave());
            toReturn = o.getValorDefecto();
            setOpcion(o, toReturn);
        }
        return toReturn;
    }

    public void setOpcion(OpcionesDouble o, double d){
        opciones.setProperty(o.getClave(), Double.toString(d));
        guardarOpciones();
    }
    protected void crearOpciones(){
        opciones.setProperty(OpcionesDouble.VOLUMEN_MUSICA.getClave(), Double.toString(OpcionesDouble.VOLUMEN_MUSICA.getValorDefecto()));
        opciones.setProperty(OpcionesDouble.VOLUMEN_SFX.getClave(), Double.toString(OpcionesDouble.VOLUMEN_MUSICA.getValorDefecto()));
        guardarOpciones();
    }
    protected void guardarOpciones(){
        try {
            FileOutputStream stream = new FileOutputStream(rutaOpciones);
            opciones.store(stream, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
