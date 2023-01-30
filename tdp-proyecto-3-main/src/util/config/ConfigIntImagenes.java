package util.config;

public enum ConfigIntImagenes {
    //Defensores
    CANT_IMAGENES_ATAQUE_MAGO("cantImagenesAtaqueMago"),
    CANT_IMAGENES_NORMAL_MAGO("cantImagenesNormalMago"),
    DURACION_LOOP_NORMAL_MAGO("duracionLoopNormalMago"),
    CANT_IMAGENES_ATAQUE_ARQUERA_HUMANA("cantImagenesAtaqueArqueraHumana"),
    CANT_IMAGENES_NORMAL_ARQUERA_HUMANA("cantImagenesNormalArqueraHumana"),
    DURACION_LOOP_NORMAL_ARQUERA_HUMANA("duracionLoopNormalArqueraHumana"),
    CANT_IMAGENES_ATAQUE_ARQUERO_ELFO("cantImagenesAtaqueArqueroElfo"),
    CANT_IMAGENES_NORMAL_ARQUERO_ELFO("cantImagenesNormalArqueroElfo"),
    DURACION_LOOP_NORMAL_ARQUERO_ELFO("duracionLoopNormalArqueroElfo"),
    CANT_IMAGENES_ATAQUE_ELFO_MELE("cantImagenesAtaqueElfoMele"),
    CANT_IMAGENES_NORMAL_ELFO_MELE("cantImagenesNormalElfoMele"),
    DURACION_LOOP_NORMAL_ELFO_MELE("duracionLoopNormalElfoMele"),
    CANT_IMAGENES_ATAQUE_CABALLERO_MELE("cantImagenesAtaqueCaballeroMele"),
    CANT_IMAGENES_NORMAL_CABALLERO_MELE("cantImagenesNormalCaballeroMele"),
    DURACION_LOOP_NORMAL_CABALLERO_MELE("duracionLoopNormalCaballeroMele"),
    CANT_IMAGENES_ATAQUE_HADA("cantImagenesAtaqueHada"),
    CANT_IMAGENES_NORMAL_HADA("cantImagenesNormalHada"),
    DURACION_LOOP_NORMAL_HADA("duracionLoopNormalHada"),
    CANT_IMAGENES_DEFENSA_ESCUDERO("cantImagenesDefensaEscudero"),
    CANT_IMAGENES_NORMAL_ESCUDERO("cantImagenesNormalEscudero"),
    DURACION_LOOP_NORMAL_ESCUDERO("duracionLoopNormalEscudero"),

    //Enemigos
    CANT_IMAGENES_ATAQUE_TROLL_MAZO_PIEDRA("cantImagenesAtaqueTrollMazoPiedra"),
    CANT_IMAGENES_MUERTE_TROLL_MAZO_PIEDRA("cantImagenesMuerteTrollMazoPiedra"),
    CANT_IMAGENES_AVANCE_TROLL_MAZO_PIEDRA("cantImagenesAvanceTrollMazoPiedra"),
    CANT_IMAGENES_ATAQUE_TROLL_TRONCO("cantImagenesAtaqueTrollTronco"),
    CANT_IMAGENES_MUERTE_TROLL_TRONCO("cantImagenesMuerteTrollTronco"),
    CANT_IMAGENES_AVANCE_TROLL_TRONCO("cantImagenesAvanceTrollTronco"),
    CANT_IMAGENES_ATAQUE_TROLL_HUESO("cantImagenesAtaqueTrollHueso"),
    CANT_IMAGENES_MUERTE_TROLL_HUESO("cantImagenesMuerteTrollHueso"),
    CANT_IMAGENES_AVANCE_TROLL_HUESO("cantImagenesAvanceTrollHueso"),
    CANT_IMAGENES_ATAQUE_ORCO_MAZO("cantImagenesAtaqueOrcoMazo"),
    CANT_IMAGENES_MUERTE_ORCO_MAZO("cantImagenesMuerteOrcoMazo"),
    CANT_IMAGENES_AVANCE_ORCO_MAZO("cantImagenesAvanceOrcoMazo"),
    CANT_IMAGENES_ATAQUE_ORCO_HACHA("cantImagenesAtaqueOrcoHacha"),
    CANT_IMAGENES_MUERTE_ORCO_HACHA("cantImagenesMuerteOrcoHacha"),
    CANT_IMAGENES_AVANCE_ORCO_HACHA("cantImagenesAvanceOrcoHacha"),
    CANT_IMAGENES_ATAQUE_ORCO_ESPADA("cantImagenesAtaqueOrcoEspada"),
    CANT_IMAGENES_MUERTE_ORCO_ESPADA("cantImagenesMuerteOrcoEspada"),
    CANT_IMAGENES_AVANCE_ORCO_ESPADA("cantImagenesAvanceOrcoEspada"),
    CANT_IMAGENES_FLECHA_HUMANA("cantImagenesFlechaHumana"),
    DURACION_LOOP_FLECHA_HUMANA("duracionLoopFlechaHumana"),
    CANT_IMAGENES_FLECHA_ELFO("cantImagenesFlechaElfo"),
    CANT_IMAGENES_ORBE("cantImagenesOrbe"),
    DURACION_LOOP_ORBE("duracionLoopOrbe"),
    DURACION_LOOP_FLECHA_ELFO("duracionLoopFlechaElfo"),
    DURACION_MENSAJES_CUENTA_ATRAS("duracionMensajesCuentaAtras"),
    DURACION_MENSAJE_NOTIFICAR_HORDA("duracionMensajeNotificarHorda"),
    DURACION_MENSAJE_GAME_OVER("duracionMensajeGameOver"),
    DURACION_MENSAJE_VICTORIA("duracionMensajeVictoria"),
    DURACION_MENSAJE_NIVEL_COMPLETADO("duracionMensajeNivelCompletado"),
    CANT_IMAGENES_ANIMACION_FONDO_MENU("cantImagenesAnimacionFondoMenu"),
    DURACION_LOOP_ANIMACION_FONDO_MENU("duracionLoopAnimacionFondoMenu");

    private final String clave;
    ConfigIntImagenes(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
