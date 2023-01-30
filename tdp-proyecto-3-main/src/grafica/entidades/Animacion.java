package grafica.entidades;

public abstract class Animacion {
    protected final String ruta;
    protected final int cantImagenes;
    protected final boolean loop;

    /**
        Si loop=true, las imágenes se repetirán a la inversa a partir del 50%.
         Es decir, si cantImagenes es 10, se considerarán 19 pasos de animación, repitiendo cada una 2 veces salvo posiblemente la del medio
    */
    public Animacion(String ruta, int cantImagenes, boolean loop){
        this.ruta = ruta;
        if(!loop)
            this.cantImagenes = cantImagenes;
        else
            this.cantImagenes = cantImagenes * 2 - 1;
        this.loop = loop;
    }

    /**
     * Obtiene la ruta de la imagen correspondiente, en base al porcentaje de avance de la animación y si está en modo loop
     */
    protected String getImagenPorcentaje(int porcentaje){
        int indice;
        if(porcentaje < 100)
            indice = (int)(porcentaje * cantImagenes / 100.0);
        else
            indice = cantImagenes - 1;
        if(loop && porcentaje > 50) // Aunque la cantidad sea impar, el caso del medio queda implícito
            indice = cantImagenes - 1 - indice;

        return ruta+"/"+ indice + ".png";
    }

    /**
     * Avanza la animación y retorna la imagen siguiente.
     * La interpretación del parámetro avance depende de cada implementación
     */
    public abstract String getImagen();
}
