package logica.cartas;

import grafica.GeneradorGraficasCartas;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.ArqueroElfo;

import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaArqueroElfo extends Carta {
    public CartaArqueroElfo() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_ARQUERO_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_ARQUERO_ELFO));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    public void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
        ArqueroElfo.create(m,b,t);
    }

}