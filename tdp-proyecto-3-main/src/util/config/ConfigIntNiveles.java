package util.config;

public enum ConfigIntNiveles {
    TIEMPO_HASTA_MONEDA("tiempoHastaMoneda"),
    TIEMPO_DESAPARECER_MONEDA("tiempoDesaparecerMoneda"),
    VALOR_MONEDA("valorMoneda"),
    COOLDOWN_HORDA("cooldownHorda"),
    CANT_ENEMIGO_1_NIVEL_PUENTE_1("cantEnemigo1NivelPuente1"),
    CANT_ENEMIGO_2_NIVEL_PUENTE_1("cantEnemigo2NivelPuente1"),
    CANT_ENEMIGO_3_NIVEL_PUENTE_1("cantEnemigo3NivelPuente1"),
    TIEMPO_HASTA_ENEMIGO_NIVEL_PUENTE_1("tiempoHastaEnemigoNivelPuente1"),
    TIEMPO_NOTIFICAR_HORDA_NIVEL_PUENTE_1("tiempoNotificarHordaNivelPuente1"),
    CANT_ENEMIGO_1_NIVEL_PUENTE_2("cantEnemigo1NivelPuente2"),
    CANT_ENEMIGO_2_NIVEL_PUENTE_2("cantEnemigo2NivelPuente2"),
    CANT_ENEMIGO_3_NIVEL_PUENTE_2("cantEnemigo3NivelPuente2"),
    TIEMPO_HASTA_ENEMIGO_NIVEL_PUENTE_2("tiempoHastaEnemigoNivelPuente2"),
    TIEMPO_NOTIFICAR_HORDA_NIVEL_PUENTE_2("tiempoNotificarHordaNivelPuente2"),
    CANT_ENEMIGO_1_NIVEL_CASTILLO_1("cantEnemigo1NivelCastillo1"),
    CANT_ENEMIGO_2_NIVEL_CASTILLO_1("cantEnemigo2NivelCastillo1"),
    CANT_ENEMIGO_3_NIVEL_CASTILLO_1("cantEnemigo3NivelCastillo1"),
    TIEMPO_HASTA_ENEMIGO_NIVEL_CASTILLO_1("tiempoHastaEnemigoNivelCastillo1"),
    TIEMPO_NOTIFICAR_HORDA_NIVEL_CASTILLO_1("tiempoNotificarHordaNivelCastillo1"),
    CANT_ENEMIGO_1_NIVEL_CASTILLO_2("cantEnemigo1NivelCastillo2"),
    CANT_ENEMIGO_2_NIVEL_CASTILLO_2("cantEnemigo2NivelCastillo2"),
    CANT_ENEMIGO_3_NIVEL_CASTILLO_2("cantEnemigo3NivelCastillo2"),
    TIEMPO_HASTA_ENEMIGO_NIVEL_CASTILLO_2("tiempoHastaEnemigoNivelCastillo2"),
    TIEMPO_NOTIFICAR_HORDA_NIVEL_CASTILLO_2("tiempoNotificarHordaNivelCastillo2"),
    MONEDAS_INICIALES_PUENTE_1("monedasInicialesPuente1"),
    MONEDAS_INICIALES_PUENTE_2("monedasInicialesPuente2"),
    MONEDAS_INICIALES_CASTILLO_1("monedasInicialesCastillo1"),
    MONEDAS_INICIALES_CASTILLO_2("monedasInicialesCastillo2"),

    ENEMIGOS_PARA_HORDA_PUENTE_1("enemigosParaHordaPuente1"),
    ENEMIGOS_PARA_HORDA_1_PUENTE_2("enemigosParaHorda1Puente2"),
    ENEMIGOS_PARA_HORDA_2_PUENTE_2("enemigosParaHorda2Puente2"),
    ENEMIGOS_PARA_HORDA_CASTILLO_1("enemigosParaHordaCastillo1"),
    ENEMIGOS_PARA_HORDA_1_CASTILLO_2("enemigosParaHorda1Castillo2"),
    ENEMIGOS_PARA_HORDA_2_CASTILLO_2("enemigosParaHorda2Castillo2");


    private final String clave;
    ConfigIntNiveles(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
