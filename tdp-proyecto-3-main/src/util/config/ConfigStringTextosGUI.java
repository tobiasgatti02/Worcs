package util.config;

public enum ConfigStringTextosGUI {
    ARCHIVO_CREDITOS("archivoCreditos"),
    ARCHIVO_LICENCIAS("archivoLicencias"),
    NOMBRE_VENTANA("nombreVentana");
    private final String clave;
    ConfigStringTextosGUI(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
