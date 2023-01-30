package grafica.entidades;

/**
 * Una animación para la que no se desea controlar la imagen que se muestra en cada momento, en base al porcentaje de avance
 */
public class AnimacionPorcentaje extends Animacion{
    protected int porcentaje;
    public AnimacionPorcentaje(String ruta, int cantImagenes, boolean loop){
        super(ruta, cantImagenes, loop);
        porcentaje = 0;
    }

    public void setPorcentaje(int porcentaje){
        this.porcentaje = porcentaje;
    }

    @Override
    public String getImagen() {
        return getImagenPorcentaje(porcentaje);
    }
}
