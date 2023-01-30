package grafica.entidades;

import grafica.GraficaTablero;
import grafica.PanelImagen;
import java.awt.Rectangle;

public abstract class GraficaEntidad extends PanelImagen {
    protected Animacion animacionActual;
    protected final GraficaTablero miTablero;
    public GraficaEntidad(GraficaTablero g, Animacion animacionInicial) {
        super();
        animacionActual = animacionInicial;
        miTablero = g;
    }

    protected void establecerImagen(String imagen) {
        imagenActual = imagen;
        repaint();
    }

    protected void init(){
        setBounds(getRectGrafica());
        actualizarAnimacion();
    }

    public void desaparecer() {
        miTablero.removerEntidad(this);
    }

    protected void actualizarAnimacion(){
        String nuevaImagen = animacionActual.getImagen();
        if(!nuevaImagen.equals(imagenActual))
           Animador.getInstance().cambiarImagen(this, nuevaImagen);
    }

    protected abstract Rectangle getRectGrafica();

}