package logica.cartas;

import grafica.GeneradorGraficasCartas;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.defensores.ArqueraHumana;

import util.config.ConfigIntCartas;
import util.config.LectorConfiguracion;

public class CartaArqueraHumana extends Carta {
    public CartaArqueraHumana() {
        super(LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.PRECIO_CARTA_ARQUERA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntCartas.COOLDOWN_CARTA_ARQUERA_HUMANA));

        miGrafica = GeneradorGraficasCartas.crearGrafica(this);
    }

    @Override
    public void crearDefensor(Motor m, Bloque b, Tablero t){
        // es responsabilidad del objeto agregarse al motor
        ArqueraHumana.create(m,b,t);
    }
}