package util.config;

public enum ConfigIntSonidos {

    POLIFONIA_MUSICA_PUENTE("polifoniaMusicaPuente"),
    POLIFONIA_MUSICA_MENU("polifoniaMusicaMenu"),
    POLIFONIA_MUSICA_CASTILLO("polifoniaMusicaCastillo"),

    POLIFONIA_SFX("polifoniaSFX");

    private final String clave;
    ConfigIntSonidos(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
