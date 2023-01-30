package grafica;

import logica.cartas.*;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

public class GeneradorGraficasCartas {
    // impide que se creen instancias de la clase
    private GeneradorGraficasCartas() {
        throw new AssertionError();
    }

    public static GraficaCarta crearGrafica(CartaMago c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_MAGO),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }

    public static GraficaCarta crearGrafica(CartaArqueraHumana c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_ARQUERA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)        );
    }

    public static GraficaCarta crearGrafica(CartaElfoMele c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_ELFO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }

    public static GraficaCarta crearGrafica(CartaCaballeroMele c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }

    public static GraficaCarta crearGrafica(CartaArqueroElfo c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_ARQUERO_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }

    public static GraficaCarta crearGrafica(CartaHada c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_HADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }

    public static GraficaCarta crearGrafica(CartaEscudero c) {
        return new GraficaCarta(
                c,
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CARTA_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_SELECCIONADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_DESACTIVADA),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.SUPERPOSICION_CARTA_COOLDOWN)
        );
    }
}