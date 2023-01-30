package logica.cartas;

import grafica.GeneradorGraficasCartas;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.Hada;

import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaHada extends Carta {
    public CartaHada() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_HADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_HADA));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    public void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
         Hada.create(m, b, t);
    }
}