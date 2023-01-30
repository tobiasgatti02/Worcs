package grafica.oyentes;

import grafica.GraficaMoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OyenteMoneda implements ActionListener {

    protected final GraficaMoneda miGrafica;
    public OyenteMoneda(GraficaMoneda g) {
        miGrafica = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        miGrafica.clicMoneda();
    }

}