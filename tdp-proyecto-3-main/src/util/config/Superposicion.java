package util.config;

public enum Superposicion {
    GameOver(ConfigStringImagenes.IMAGEN_GAME_OVER,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_GAME_OVER,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_GAME_OVER,
            ConfigIntImagenes.DURACION_MENSAJE_GAME_OVER),

    CuentaAtras3(ConfigStringImagenes.IMAGEN_CUENTA_ATRAS_3,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_CUENTA_ATRAS_3,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_CUENTA_ATRAS_3,
            ConfigIntImagenes.DURACION_MENSAJES_CUENTA_ATRAS),

    CuentaAtras2(ConfigStringImagenes.IMAGEN_CUENTA_ATRAS_2,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_CUENTA_ATRAS_2,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_CUENTA_ATRAS_2,
            ConfigIntImagenes.DURACION_MENSAJES_CUENTA_ATRAS),

    CuentaAtras1(ConfigStringImagenes.IMAGEN_CUENTA_ATRAS_1,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_CUENTA_ATRAS_1,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_CUENTA_ATRAS_1,
            ConfigIntImagenes.DURACION_MENSAJES_CUENTA_ATRAS),

    CuentaAtras0(ConfigStringImagenes.IMAGEN_CUENTA_ATRAS_0,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_CUENTA_ATRAS_0,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_CUENTA_ATRAS_0,
            ConfigIntImagenes.DURACION_MENSAJES_CUENTA_ATRAS),

    NotificarHorda(ConfigStringImagenes.IMAGEN_NOTIFICAR_HORDA,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_NOTIFICAR_HORDA,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_NOTIFICAR_HORDA,
            ConfigIntImagenes.DURACION_MENSAJE_NOTIFICAR_HORDA),

    Victoria(ConfigStringImagenes.IMAGEN_VICTORIA,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_VICTORIA,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_VICTORIA,
            ConfigIntImagenes.DURACION_MENSAJE_VICTORIA),

    NivelCompletado(ConfigStringImagenes.IMAGEN_NIVEL_COMPLETADO,
            ConfigDoubleImagenes.PORCENTAJE_ANCHO_NIVEL_COMPLETADO,
            ConfigDoubleImagenes.PORCENTAJE_ALTO_NIVEL_COMPLETADO,
            ConfigIntImagenes.DURACION_MENSAJE_NIVEL_COMPLETADO);



    private final ConfigStringImagenes imagen;
    private final ConfigDoubleImagenes porcentajeAncho;
    private final ConfigDoubleImagenes porcentajeAlto;
    private final ConfigIntImagenes duracion;

    Superposicion(ConfigStringImagenes imagen, ConfigDoubleImagenes porcentajeAncho, ConfigDoubleImagenes porcentajeAlto, ConfigIntImagenes duracion) {
        this.imagen = imagen;
        this.porcentajeAncho = porcentajeAncho;
        this.porcentajeAlto = porcentajeAlto;
        this.duracion = duracion;
    }

    public ConfigStringImagenes getImagen() {
        return imagen;
    }

    public ConfigDoubleImagenes getPorcentajeAncho() {
        return porcentajeAncho;
    }

    public ConfigDoubleImagenes getPorcentajeAlto() {
        return porcentajeAlto;
    }

    public ConfigIntImagenes getDuracion() {
        return duracion;
    }
}
