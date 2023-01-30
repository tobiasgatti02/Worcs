package grafica.entidades;


/**
 * Una animación para la que no se desea controlar la imagen que se muestra en cada momento (similar a un gif animado)
 */
public class AnimacionTemporizada extends Animacion{
    protected final int duracionLoop;
    protected int tiempoTranscurrido;
    public AnimacionTemporizada(String ruta, int cantImagenes, int duracionLoop){
        super(ruta, cantImagenes, false);
        this.duracionLoop = duracionLoop;
    }
    public void avanzar(int tiempo){
        tiempoTranscurrido += tiempo;
        if(tiempoTranscurrido > duracionLoop)
            tiempoTranscurrido -= duracionLoop;
    }

    @Override
    public String getImagen(){
        return getImagenPorcentaje(tiempoTranscurrido * 100 / duracionLoop);
    }
}
