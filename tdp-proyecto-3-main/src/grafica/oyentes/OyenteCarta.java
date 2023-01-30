package grafica.oyentes;

import grafica.GraficaCarta;
import grafica.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OyenteCarta implements ActionListener {
    protected final Ventana miVentana;
    protected final GraficaCarta miGrafica;
    public OyenteCarta(GraficaCarta g, Ventana v) {
        miVentana = v;
        miGrafica = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        miVentana.alternarSeleccion(miGrafica);
    }


}