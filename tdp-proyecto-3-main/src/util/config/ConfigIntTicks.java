package util.config;

public enum ConfigIntTicks {

    PORCENTAJE_VELOCIDAD("porcentajeVelocidad"),
    TIEMPO_POR_TICK_MOTOR("tiempoPorTickMotor"),
    TIEMPO_POR_TICK_ESTRATEGIAS("tiempoPorTickEstrategias"),
    TIEMPO_POR_TICK_CARTAS("tiempoPorTickCartas"),
    DELAY_ENEMIGOS("delayEnemigos"),
    DELAY_DEFENSORES("delayDefensores"),
    DELAY_PROYECTILES("delayProyectiles");

    private final String clave;
    ConfigIntTicks(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
