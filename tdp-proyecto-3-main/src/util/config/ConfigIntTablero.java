package util.config;

public enum ConfigIntTablero {
    CANT_FILAS("cantFilas"),
    CANT_COLUMNAS("cantColumnas");

    private final String clave;
    ConfigIntTablero(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
