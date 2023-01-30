package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.proyectiles.*;
import util.config.ConfigIntImagenes;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

public class GeneradorGraficaProyectiles {
    // impide que se creen instancias de la clase
    private GeneradorGraficaProyectiles() {
        throw new AssertionError();
    }

    public static GraficaProyectil crearGrafica(FlechaElfo p, GraficaTablero g) {
        AnimacionTemporizada animacion = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FLECHA_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_FLECHA_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_FLECHA_ELFO));
        return new GraficaProyectil(
                p,
                g,
                animacion
        );
    }

    public static GraficaProyectil crearGrafica(FlechaHumana p, GraficaTablero g) {
        AnimacionTemporizada animacion = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FLECHA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_FLECHA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_FLECHA_HUMANA));
        return new GraficaProyectil(
                p,
                g,
                animacion
        );
    }

    public static GraficaProyectil crearGrafica(Orbe p, GraficaTablero g) {
        AnimacionTemporizada animacion = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ORBE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ORBE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_ORBE));
        return new GraficaProyectil(
                p,
                g,
                animacion
        );
    }
}