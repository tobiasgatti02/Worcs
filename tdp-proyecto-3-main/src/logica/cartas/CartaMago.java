package logica.cartas;

import grafica.GeneradorGraficasCartas;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.Mago;

import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaMago extends Carta {
    public CartaMago() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_MAGO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_MAGO));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    protected void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
        Mago.create(m,b,t);
    }
}