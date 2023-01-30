package util.config;

public enum ConfigStringSonidos {
    RUTA_MUSICA_PUENTE("rutaMusicaPuente"),
    RUTA_MUSICA_MENU("rutaMusicaMenu"),
    RUTA_MUSICA_CASTILLO("rutaMusicaCastillo"),
    RUTA_FLECHAZO_HUMANA("rutaFlechazoHumana"),
    RUTA_GOLPE_TROLL2("rutaFlechazoElfo"),

    RUTA_DISPARO_MAGO("rutaDisparoMago"),

    RUTA_ESPADAZO_CABALLERO1("rutaEspadazoCaballero1"),

    RUTA_ESPADAZO_CABALLERO2("rutaEspadazoCaballero2"),

    RUTA_GOLPE_TROLL("rutaGolpeTroll"),
    RUTA_ESPADA_ENEMIGO("rutaEspadaEnemigo"),
    RUTA_HADA_MONEDA("rutaHadaMoneda"),
    RUTA_RECOGER_MONEDA("rutaRecogerMoneda"),
    RUTA_MUERTE_ORCO("rutaMuerteOrco"),
    RUTA_SONIDO_PALA("rutaPala"),
    RUTA_CARTA_SELECCIONADA("rutaCartaSeleccionada"),
    RUTA_DEFENSOR_COLOCADO("rutaDefensorColocado"),
    RUTA_MUERTE_HADA("rutaMuerteHada"),
    RUTA_MUERTE_DEFENSOR_NO_HADA("rutaMuerteDefensorNoHada"),
    RUTA_MAZO_GOLPE("rutaMazoGolpe"),
    GAME_OVER("rutaGameOver"),
    NOTIFICAR_HORDA("rutaNotificarHorda");

    private final String clave;
    ConfigStringSonidos(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
