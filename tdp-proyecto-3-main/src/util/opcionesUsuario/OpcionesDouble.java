package util.opcionesUsuario;

public enum OpcionesDouble {
    VOLUMEN_MUSICA("volumenMusica", 1),
    VOLUMEN_SFX("volumenSFX", 1);
    private final String clave;
    private final double valorDefecto;
    OpcionesDouble(String clave, double valorDefecto){
        this.clave = clave;
        this.valorDefecto = valorDefecto;
    }
    public String getClave(){
        return clave;
    }

    public double getValorDefecto() {
        return valorDefecto;
    }
}
