package util.config;

public enum ConfigIntCartas {

    PRECIO_CARTA_ARQUERA_HUMANA("precioCartaArqueraHumana"),
    COOLDOWN_CARTA_ARQUERA_HUMANA("cooldownCartaArqueraHumana"),
    PRECIO_CARTA_ARQUERO_ELFO("precioCartaArqueroElfo"),
    COOLDOWN_CARTA_ARQUERO_ELFO("cooldownCartaArqueroElfo"),
    PRECIO_CARTA_CABALLERO_MELE("precioCartaCaballeroMele"),
    COOLDOWN_CARTA_CABALLERO_MELE("cooldownCartaCaballeroMele"),
    PRECIO_CARTA_ELFO_MELE("precioCartaElfoMele"),
    COOLDOWN_CARTA_ELFO_MELE("cooldownCartaElfoMele"),
    PRECIO_CARTA_HADA("precioCartaHada"),
    COOLDOWN_CARTA_HADA("cooldownCartaHada"),
    PRECIO_CARTA_MAGO("precioCartaMago"),
    COOLDOWN_CARTA_MAGO("cooldownCartaMago"),
    PRECIO_CARTA_ESCUDERO("precioCartaEscudero"),
    COOLDOWN_CARTA_ESCUDERO("cooldownCartaEscudero");

    private final String clave;
    ConfigIntCartas(String clave){
        this.clave = clave;
    }
    public String getClave(){
        return clave;
    }
}
