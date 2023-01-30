package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.defensores.*;
import util.config.ConfigDoubleGraficas;
import util.config.ConfigIntImagenes;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

public class GeneradorGraficaDefensores {
    // impide que se creen instancias de la clase
    private GeneradorGraficaDefensores() {
        throw new AssertionError();
    }

    public static GraficaDefensor crearGrafica(Mago d, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_MAGO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_MAGO),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_MAGO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_MAGO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_MAGO));

        return new GraficaDefensorGenerico(
                d,
                g,
                animacionAtaque,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_MAGO)
        );
    }

    public static GraficaDefensor crearGrafica(ArqueraHumana d, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ARQUERA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ARQUERA_HUMANA),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_ARQUERA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_ARQUERA_HUMANA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_ARQUERA_HUMANA));

        return new GraficaDefensorGenerico(
                d,
                g,
                animacionAtaque,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ARQUERA_HUMANA)
        );
    }

    public static GraficaDefensor crearGrafica(ArqueroElfo d, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ARQUERO_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ARQUERO_ELFO),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_ARQUERO_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_ARQUERO_ELFO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_ARQUERO_ELFO));

        return new GraficaDefensorGenerico(
                d,
                g,
                animacionAtaque,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ARQUERO_ELFO)
        );
    }

    public static GraficaDefensor crearGrafica(ElfoMele d, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_ELFO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_ELFO_MELE),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_ELFO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_ELFO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_ELFO_MELE));

        return new GraficaDefensorMele(
                d,
                g,
                animacionAtaque,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ELFO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ELFO_MELE)
        );
    }

    public static GraficaDefensor crearGrafica(CaballeroMele d, GraficaTablero g) {
        AnimacionPorcentaje animacionAtaque = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_CABALLERO_MELE),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_CABALLERO_MELE));

        return new GraficaDefensorMele(
                d,
                g,
                animacionAtaque,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_CABALLERO_MELE),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_CABALLERO_MELE)
        );
    }

    public static GraficaDefensor crearGrafica(Hada d, GraficaTablero g) {
        AnimacionPorcentaje animacionHabilidad = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ATAQUE_HADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ATAQUE_HADA),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_HADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_HADA),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_HADA));

        return new GraficaDefensorGenerico(
                d,
                g,
                animacionHabilidad,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_HADA)
        );
    }

    public static GraficaDefensor crearGrafica(Escudero d, GraficaTablero g) {
        AnimacionPorcentaje animacionHabilidad = new AnimacionPorcentaje(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_DEFENSA_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_DEFENSA_ESCUDERO),
                false);
        AnimacionTemporizada animacionNormal = new AnimacionTemporizada(
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_NORMAL_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_NORMAL_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_NORMAL_ESCUDERO));

        return new GraficaEscudero(
                d,
                g,
                animacionHabilidad,
                animacionNormal,
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ANCHO_ESCUDERO),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleGraficas.PORCENTAJE_EXTRA_ALTO_ESCUDERO)
        );
    }
}