package util.config;

public enum ConfigDoubleTablero {

    ANCHO("ancho"),
    ALTO("alto"),
    ANCHO_MONEDA("anchoMoneda"),
    ALTO_MONEDA("altoMoneda");
    private final String clave;
    ConfigDoubleTablero(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
