package util.config;

public enum ConfigDoubleGraficas {

    PORCENTAJE_EXTRA_ALTO_MAGO("porcentajeExtraAltoMago"),
    PORCENTAJE_EXTRA_ALTO_ARQUERA_HUMANA("porcentajeExtraAltoArqueraHumana"),
    PORCENTAJE_EXTRA_ALTO_ARQUERO_ELFO("porcentajeExtraAltoArqueroElfo"),
    PORCENTAJE_EXTRA_ANCHO_ELFO_MELE("porcentajeExtraAnchoElfoMele"),
    PORCENTAJE_EXTRA_ALTO_ELFO_MELE("porcentajeExtraAltoElfoMele"),
    PORCENTAJE_EXTRA_ANCHO_CABALLERO_MELE("porcentajeExtraAnchoCaballeroMele"),
    PORCENTAJE_EXTRA_ALTO_CABALLERO_MELE("porcentajeExtraAltoCaballeroMele"),
    PORCENTAJE_EXTRA_ALTO_HADA("porcentajeExtraAltoHada"),

    PORCENTAJE_EXTRA_ALTO_ESCUDERO("porcentajeExtraAltoEscudero"),
    PORCENTAJE_EXTRA_ANCHO_ESCUDERO("porcentajeExtraAnchoEscudero"),

    PORCENTAJE_EXTRA_ANCHO_ORCO_HACHA("porcentajeExtraAnchoOrcoHacha"),
    PORCENTAJE_EXTRA_ALTO_ORCO_HACHA("porcentajeExtraAltoOrcoHacha"),
    PORCENTAJE_EXTRA_ANCHO_ORCO_HACHA_MUERTO("porcentajeExtraAnchoOrcoHachaMuerto"),
    PORCENTAJE_EXTRA_ALTO_ORCO_HACHA_MUERTO("porcentajeExtraAltoOrcoHachaMuerto"),

    PORCENTAJE_EXTRA_ANCHO_ORCO_ESPADA("porcentajeExtraAnchoOrcoEspada"),
    PORCENTAJE_EXTRA_ALTO_ORCO_ESPADA("porcentajeExtraAltoOrcoEspada"),

    PORCENTAJE_EXTRA_ANCHO_ORCO_ESPADA_MUERTO("porcentajeExtraAnchoOrcoEspadaMuerto"),
    PORCENTAJE_EXTRA_ALTO_ORCO_ESPADA_MUERTO("porcentajeExtraAltoOrcoEspadaMuerto"),

    PORCENTAJE_EXTRA_ANCHO_ORCO_MAZO("porcentajeExtraAnchoOrcoMazo"),
    PORCENTAJE_EXTRA_ALTO_ORCO_MAZO("porcentajeExtraAltoOrcoMazo"),
    PORCENTAJE_EXTRA_ANCHO_ORCO_MAZO_MUERTO("porcentajeExtraAnchoOrcoMazoMuerto"),
    PORCENTAJE_EXTRA_ALTO_ORCO_MAZO_MUERTO("porcentajeExtraAltoOrcoMazoMuerto"),

    PORCENTAJE_EXTRA_ANCHO_TROLL_HUESO("porcentajeExtraAnchoTrollHueso"),
    PORCENTAJE_EXTRA_ALTO_TROLL_HUESO("porcentajeExtraAltoTrollHueso"),
    PORCENTAJE_EXTRA_ANCHO_TROLL_HUESO_MUERTO("porcentajeExtraAnchoTrollHuesoMuerto"),
    PORCENTAJE_EXTRA_ALTO_TROLL_HUESO_MUERTO("porcentajeExtraAltoTrollHuesoMuerto"),


    PORCENTAJE_EXTRA_ANCHO_TROLL_TRONCO("porcentajeExtraAnchoTrollTronco"),
    PORCENTAJE_EXTRA_ALTO_TROLL_TRONCO("porcentajeExtraAltoTrollTronco"),
    PORCENTAJE_EXTRA_ANCHO_TROLL_TRONCO_MUERTO("porcentajeExtraAnchoTrollTroncoMuerto"),
    PORCENTAJE_EXTRA_ALTO_TROLL_TRONCO_MUERTO("porcentajeExtraAltoTrollTroncoMuerto"),


    PORCENTAJE_EXTRA_ANCHO_TROLL_MAZO_PIEDRA("porcentajeExtraAnchoTrollMazoPiedra"),
    PORCENTAJE_EXTRA_ALTO_TROLL_MAZO_PIEDRA("porcentajeExtraAltoTrollMazoPiedra"),
    PORCENTAJE_EXTRA_ANCHO_TROLL_MAZO_PIEDRA_MUERTO("porcentajeExtraAnchoTrollMazoPiedraMuerto"),
    PORCENTAJE_EXTRA_ALTO_TROLL_MAZO_PIEDRA_MUERTO("porcentajeExtraAltoTrollMazoPiedraMuerto");


    private final String clave;
    ConfigDoubleGraficas(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
