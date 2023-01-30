package util.config;

public enum ConfigIntEntidades {

    SALUD_ORCO_ESPADA("saludOrcoEspada"),
    DAMAGE_ORCO_ESPADA("damageOrcoEspada"),
    TIEMPO_MUERTE_ORCO_ESPADA("tiempoMuerteOrcoEspada"),
    TIEMPO_MUERTE_ORCO_MAZO("tiempoMuerteOrcoMazo"),
    TIEMPO_MUERTE_ORCO_HACHA("tiempoMuerteOrcoHacha"),
    TIEMPO_MUERTE_TROLL_HUESO("tiempoMuerteTrollHueso"),
    TIEMPO_MUERTE_TROLL_MAZO_PIEDRA("tiempoMuerteTrollMazoPiedra"),
    TIEMPO_MUERTE_TROLL_TRONCO("tiempoMuerteTrollTronco"),
    SALUD_MAGO("saludMago"),
    SALUD_ARQUERO_ELFO("saludArqueroElfo"),
    SALUD_ARQUERA_HUMANA("saludArqueraHumana"),
    TIEMPO_ATAQUE_MAGO("tiempoAtaqueMago"),
    TIEMPO_ATAQUE_ARQUERA_HUMANA("tiempoAtaqueArqueraHumana"),
    TIEMPO_ATAQUE_ARQUERO_ELFO("tiempoAtaqueArqueroElfo"),
    TIEMPO_ATAQUE_ORCO_ESPADA("tiempoAtaqueOrcoEspada"),
    DAMAGE_FLECHA_ELFO("damageFlechaElfo"),
    DAMAGE_FLECHA_HUMANA("damageFlechaHumana"),
    DAMAGE_ORBE("damageOrbe"),
    DURACION_EFECTO_ORBE("duracionEfectoOrbe"),
    TIEMPO_ATAQUE_ELFO_MELE("tiempoAtaqueElfoMele"),
    DAMAGE_ELFO_MELE("damageElfoMele"),
    SALUD_ELFO_MELE("saludElfoMele"),
    SALUD_CABALLERO_MELE("saludCaballeroMele"),
    TIEMPO_ATAQUE_CABALLERO_MELE("tiempoAtaqueCaballeroMele"),
    DAMAGE_ORCO_HACHA("damageOrcoHacha"),
    TIEMPO_ATAQUE_ORCO_HACHA("tiempoAtaqueOrcoHacha"),
    SALUD_ORCO_HACHA("saludOrcoHacha"),
    DAMAGE_CABALLERO_MELE("damageCaballeroMele"),
    SALUD_ORCO_MAZO("saludOrcoMazo"),
    TIEMPO_ATAQUE_ORCO_MAZO("tiempoAtaqueOrcoMazo"),
    DAMAGE_ORCO_MAZO("damageOrcoMazo"),
    AVANCE_POR_SEGUNDO_ORCO_MAZO("avancePorSegundoOrcoMazo"),
    SALUD_TROLL_HUESO("saludTrollHueso"),
    TIEMPO_ATAQUE_TROLL_HUESO("tiempoAtaqueTrollHueso"),
    DAMAGE_TROLL_HUESO("damageTrollHueso"),
    SALUD_TROLL_MAZO_PIEDRA("saludTrollMazoPiedra"),
    TIEMPO_ATAQUE_TROLL_MAZO_PIEDRA("tiempoAtaqueTrollMazoPiedra"),
    DAMAGE_TROLL_MAZO_PIEDRA("damageTrollMazoPiedra"),
    SALUD_TROLL_TRONCO("saludTrollTronco"),
    TIEMPO_ATAQUE_TROLL_TRONCO("tiempoAtaqueTrollTronco"),
    DAMAGE_TROLL_TRONCO("damageTrollTronco"),
    SALUD_HADA("saludHada"),

    SALUD_ESCUDERO("saludEscudero"),
    TIEMPO_EQUIPAR_ESCUDO_ESCUDERO("tiempoEquiparEscudoEscudero"),
    TIEMPO_MONEDA_HADA("tiempoMonedaHada"),
    TIEMPO_CREACION_MONEDA_HADA("tiempoCreacionMonedaHada");


    private final String clave;
    ConfigIntEntidades(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
