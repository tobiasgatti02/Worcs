package grafica.oyentes;

import grafica.GraficaBloque;
import grafica.Ventana;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OyenteBloque extends MouseAdapter {
    private final GraficaBloque miGrafica;
    private final Ventana miVentana;

    public OyenteBloque(GraficaBloque g, Ventana v){
        miGrafica = g;
        miVentana = v;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        miVentana.clicEnBloque(miGrafica);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        miVentana.mouseInBloque(miGrafica);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        miVentana.mouseOutBloque(miGrafica);
    }
}