package grafica;

import javax.swing.*;
import java.awt.GraphicsEnvironment;

public class UtilidadesGraficas {

    // impide que se creen instancias de la clase
    private UtilidadesGraficas() {
        throw new AssertionError();
    }
    public static void ejecutarEnEDT(Runnable r) {
        if(!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(r);
        }
        else {
            r.run();
        }
    }

    public static int escalarFuenteSegunResolucion(int size){
        int altura = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        return size * altura / 1080;
    }
}
