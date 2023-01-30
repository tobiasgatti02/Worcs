package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.enemigos.*;
import util.config.*;

public class GeneradorGraficaEnemigos {
    // impide que se creen instancias de la clase
    private GeneradorGraficaEnemigos() {
        throw new AssertionError();
    }

    public static GraficaEnemigo crearGrafica(TrollMazoPiedra e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_TROLL_MAZO_PIEDRA),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_TROLL_MAZO_PIEDRA),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_TROLL_MAZO_PIEDRA),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_TROLL_MAZO_PIEDRA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_MAZO_PIEDRA_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_MAZO_PIEDRA_MUERTO)
        );
    }

    public static GraficaEnemigo crearGrafica(TrollTronco e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_TROLL_TRONCO),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_TROLL_TRONCO),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_TROLL_TRONCO),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_TROLL_TRONCO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_TRONCO_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_TRONCO_MUERTO)
            );
    }

    public static GraficaEnemigo crearGrafica(TrollHueso e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_TROLL_HUESO),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_TROLL_HUESO),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_TROLL_HUESO),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_TROLL_HUESO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_TROLL_HUESO_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_TROLL_HUESO_MUERTO)

        );
    }

    public static GraficaEnemigo crearGrafica(OrcoMazo e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ORCO_MAZO),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_ORCO_MAZO),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_ORCO_MAZO),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_ORCO_MAZO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_MAZO_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_MAZO_MUERTO)
        );
    }

    public static GraficaEnemigo crearGrafica(OrcoHacha e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ORCO_HACHA),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_ORCO_HACHA),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_ORCO_HACHA),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_ORCO_HACHA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_HACHA_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_HACHA_MUERTO)
        );
    }

    public static GraficaEnemigo crearGrafica(OrcoEspada e, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ORCO_ESPADA),
                false);
        AnimacionPorcentaje animacionMuerte = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MUERTE_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_MUERTE_ORCO_ESPADA),
                false);
        AnimacionPorcentaje animacionAvance = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_AVANCE_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_AVANCE_ORCO_ESPADA),
                false);
        return new GraficaEnemigo(
                e,
                g,
                animacionAtaque,
                animacionMuerte,
                animacionAvance,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.DISTANCIA_ANIMACION_AVANCE_ORCO_ESPADA),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ORCO_ESPADA_MUERTO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ORCO_ESPADA_MUERTO)
        );
    }
}