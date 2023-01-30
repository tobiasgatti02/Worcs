package logica.cartas;

import grafica.GeneradorGraficasCartas;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.CaballeroMele;

import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaCaballeroMele extends Carta {
    public CartaCaballeroMele() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_CABALLERO_MELE));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    public void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
        CaballeroMele.create(m, b, t);
    }
}