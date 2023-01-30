package logica.cartas;

import grafica.GeneradorGraficasCartas;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.Escudero;
import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaEscudero extends Carta {
    public CartaEscudero() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_ESCUDERO));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    public void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
        Escudero.create(m,b,t);
    }
}