package util.config;

public enum ConfigBooleanGraficas {

    MOSTRAR_HITBOX("mostrarHitbox");
    private final String clave;
    ConfigBooleanGraficas(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
