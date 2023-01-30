package util.config;

public enum ConfigDoubleEntidades {
    AVANCE_POR_SEGUNDO_ORCO_ESPADA("avancePorSegundoOrcoEspada"),
    ALTURA_ATAQUE_MAGO("alturaAtaqueMago"),
    ALTURA_ATAQUE_ARQUERA_HUMANA("alturaAtaqueArqueraHumana"),
    ALTURA_ATAQUE_ARQUERO_ELFO("alturaAtaqueArqueroElfo"),
    AVANCE_POR_SEGUNDO_FLECHA_ELFO("avancePorSegundoFlechaElfo"),
    AVANCE_POR_SEGUNDO_FLECHA_HUMANA("avancePorSegundoFlechaHumana"),
    AVANCE_POR_SEGUNDO_ORBE("avancePorSegundoOrbe"),
    REALENTIZACION_ORBE("realentizacionOrbe"),
    ANCHO_HITBOX_ELFO_MELE("anchoHitboxElfoMele"),
    ALTO_HITBOX_ELFO_MELE("altoHitboxElfoMele"),
    ANCHO_RANGO_ELFO_MELE("anchoRangoElfoMele"),

    ALTO_HITBOX_ORCO_ESPADA("altoHitboxOrcoEspada"),
    ANCHO_HITBOX_ORCO_ESPADA("anchoHitboxOrcoEspada"),
    ANCHO_RANGO_ORCO_ESPADA("anchoRangoOrcoEspada"),
    ALTO_HITBOX_MAGO("altoHitboxMago"),
    ANCHO_HITBOX_MAGO("anchoHitboxMago"),
    ALTO_RANGO_MAGO("altoRangoMago"),
    ANCHO_RANGO_MAGO("anchoRangoMago"),
    ALTO_HITBOX_ARQUERO_ELFO("altoHitboxArqueroElfo"),
    ANCHO_HITBOX_ARQUERO_ELFO("anchoHitboxArqueroElfo"),
    ALTO_RANGO_ARQUERO_ELFO("altoRangoArqueroElfo"),
    ANCHO_RANGO_ARQUERO_ELFO("anchoRangoArqueroElfo"),
    ALTO_HITBOX_ARQUERA_HUMANA("altoHitboxArqueraHumana"),
    ANCHO_HITBOX_ARQUERA_HUMANA("anchoHitboxArqueraHumana"),
    ALTO_RANGO_ARQUERA_HUMANA("altoRangoArqueraHumana"),
    ANCHO_RANGO_ARQUERA_HUMANA("anchoRangoArqueraHumana"),
    ANCHO_HITBOX_ESCUDERO("anchoHitboxEscudero"),
    ALTO_HITBOX_ESCUDERO("altoHitboxEscudero"),
    ANCHO_RANGO_ESCUDERO("anchoRangoEscudero"),
    ALTO_HITBOX_FLECHA_ELFO("altoHitboxFlechaElfo"),
    ANCHO_HITBOX_FLECHA_ELFO("anchoHitboxFlechaElfo"),
    ALTO_HITBOX_FLECHA_HUMANA("altoHitboxFlechaHumana"),
    ANCHO_HITBOX_FLECHA_HUMANA("anchoHitboxFlechaHumana"),
    ALTO_HITBOX_ORBE("altoHitboxOrbe"),
    ANCHO_HITBOX_ORBE("anchoHitboxOrbe"),
    AVANCE_POR_SEGUNDO_ORCO_HACHA("avancePorSegundoOrcoHacha"),
    ANCHO_RANGO_ORCO_HACHA("anchoRangoOrcoHacha"),
    ANCHO_RANGO_TROLL_HUESO("anchoRangoTrollHueso"),
    AVANCE_POR_SEGUNDO_TROLL_HUESO("avancePorSegundoTrollHueso"),
    ANCHO_RANGO_TROLL_MAZO_PIEDRA("anchoRangoTrollMazoPiedra"),
    AVANCE_POR_SEGUNDO_TROLL_MAZO_PIEDRA("avancePorSegundoTrollMazoPiedra"),
    ANCHO_RANGO_TROLL_TRONCO("anchoRangoTrollTronco"),
    AVANCE_POR_SEGUNDO_TROLL_TRONCO("avancePorSegundoTrollTronco"),


    ANCHO_HITBOX_ORCO_HACHA("anchoHitboxOrcoHacha"),
    ALTO_HITBOX_ORCO_HACHA("altoHitboxOrcoHacha"),
    ANCHO_HITBOX_TROLL_HUESO("anchoHitboxTrollHueso"),
    ALTO_HITBOX_TROLL_HUESO("altoHitboxTrollHueso"),
    ANCHO_HITBOX_TROLL_MAZO_PIEDRA("anchoHitboxTrollMazoPiedra"),
    ALTO_HITBOX_TROLL_MAZO_PIEDRA("altoHitboxTrollMazoPiedra"),
    ANCHO_HITBOX_TROLL_TRONCO("anchoHitboxTrollTronco"),
    ALTO_HITBOX_TROLL_TRONCO("altoHitboxTrollTronco"),
    ANCHO_HITBOX_ORCO_MAZO("anchoHitboxOrcoMazo"),
    ALTO_HITBOX_ORCO_MAZO("altoHitboxOrcoMazo"),

    ANCHO_RANGO_ORCO_MAZO("anchoRangoOrcoMazo"),

    ANCHO_HITBOX_CABALLERO_MELE("anchoHitboxCaballeroMele"),
    ALTO_HITBOX_CABALLERO_MELE("altoHitboxCaballeroMele"),
    ANCHO_RANGO_CABALLERO_MELE("anchoRangoCaballeroMele"),

    ANCHO_HITBOX_HADA("anchoHitboxHada"),
    ALTO_HITBOX_HADA("altoHitboxHada");

    private final String clave;
    ConfigDoubleEntidades(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
