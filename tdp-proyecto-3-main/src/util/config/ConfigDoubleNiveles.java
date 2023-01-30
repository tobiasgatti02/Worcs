package util.config;

public enum ConfigDoubleNiveles {
    PROBABILIDAD_PAREJA("probabilidadPareja"),
    TIEMPO_PRIMER_ENEMIGO("tiempoPrimerEnemigo"),
    OFFSET_HORDA("offsetHorda");

    private final String clave;
    ConfigDoubleNiveles(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
