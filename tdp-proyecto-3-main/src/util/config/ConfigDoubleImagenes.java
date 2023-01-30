package util.config;

public enum ConfigDoubleImagenes {
    DISTANCIA_ANIMACION_AVANCE_TROLL_MAZO_PIEDRA("distanciaAnimacionAvanceTrollMazoPiedra"),
    DISTANCIA_ANIMACION_AVANCE_TROLL_TRONCO("distanciaAnimacionAvanceTrollTronco"),
    DISTANCIA_ANIMACION_AVANCE_TROLL_HUESO("distanciaAnimacionAvanceTrollHueso"),
    DISTANCIA_ANIMACION_AVANCE_ORCO_MAZO("distanciaAnimacionAvanceOrcoMazo"),
    DISTANCIA_ANIMACION_AVANCE_ORCO_HACHA("distanciaAnimacionAvanceOrcoHacha"),
    DISTANCIA_ANIMACION_AVANCE_ORCO_ESPADA("distanciaAnimacionAvanceOrcoEspada"),

    PORCENTAJE_ANCHO_CUENTA_ATRAS_3("porcentajeAnchoCuentaAtras3"),
    PORCENTAJE_ALTO_CUENTA_ATRAS_3("porcentajeAltoCuentaAtras3"),

    PORCENTAJE_ANCHO_CUENTA_ATRAS_2("porcentajeAnchoCuentaAtras2"),
    PORCENTAJE_ALTO_CUENTA_ATRAS_2("porcentajeAltoCuentaAtras2"),

    PORCENTAJE_ANCHO_CUENTA_ATRAS_1("porcentajeAnchoCuentaAtras1"),
    PORCENTAJE_ALTO_CUENTA_ATRAS_1("porcentajeAltoCuentaAtras1"),

    PORCENTAJE_ANCHO_CUENTA_ATRAS_0("porcentajeAnchoCuentaAtras0"),
    PORCENTAJE_ALTO_CUENTA_ATRAS_0("porcentajeAltoCuentaAtras0"),
    PORCENTAJE_ANCHO_NOTIFICAR_HORDA("porcentajeAnchoNotificarHorda"),
    PORCENTAJE_ALTO_NOTIFICAR_HORDA("porcentajeAltoNotificarHorda"),
    PORCENTAJE_ANCHO_GAME_OVER("porcentajeAnchoGameOver"),
    PORCENTAJE_ALTO_GAME_OVER("porcentajeAltoGameOver"),
    PORCENTAJE_ANCHO_NIVEL_COMPLETADO("porcentajeAnchoNivelCompletado"),
    PORCENTAJE_ALTO_NIVEL_COMPLETADO("porcentajeAltoNivelCompletado"),
    PORCENTAJE_ANCHO_VICTORIA("porcentajeAnchoVictoria"),
    PORCENTAJE_ALTO_VICTORIA("porcentajeAltoVictoria"),
    FRACCION_ANCHO_BORDE_SLIDER_VOLUMEN("fraccionAnchoBordeSliderVolumen"),
    FRACCION_ALTO_BORDE_SLIDER_VOLUMEN("fraccionAltoBordeSliderVolumen"),
    FRACCION_ANCHO_THUMB_SLIDER_VOLUMEN("fraccionAnchoThumbSliderVolumen"),
    PORCENTAJE_ANCHO_BARRA_INDICADOR_PROGRESO("porcentajeAnchoBarraIndicadorProgreso"),
    PORCENTAJE_ALTO_BARRA_INDICADOR_PROGRESO("porcentajeAltoBarraIndicadorProgreso"),
    PORCENTAJE_X_BARRA_INDICADOR_PROGRESO("porcentajeXBarraIndicadorProgreso"),
    PORCENTAJE_Y_BARRA_INDICADOR_PROGRESO("porcentajeYBarraIndicadorProgreso"),
    PORCENTAJE_X_BORDE_INDICADOR_PROGRESO("porcentajeXBordeIndicadorProgreso"),
    PORCENTAJE_Y_BORDE_INDICADOR_PROGRESO("porcentajeYBordeIndicadorProgreso"),
    PORCENTAJE_Y_ABAJO_BANDERA("porcentajeYAbajoBandera"),
    PORCENTAJE_ANCHO_BANDERA_INDICADOR_PROGRESO("porcentajeAnchoBanderaIndicadorProgreso");

    private final String clave;
    ConfigDoubleImagenes(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
