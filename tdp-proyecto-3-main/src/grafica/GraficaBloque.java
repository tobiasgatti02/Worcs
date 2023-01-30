package grafica;
import logica.Bloque;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

public class GraficaBloque extends PanelImagen {
    protected final String imagenNormal;
    protected final String imagenHover;
    protected final Bloque miBloque;

    public GraficaBloque(Bloque b, int fila, int columna) {
        miBloque = b;
        if((fila + columna) % 2 == 0)
            imagenActual = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BLOQUE_PAR);
        else
            imagenActual = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BLOQUE_IMPAR);
        setOpaque(false);
        imagenHover = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_HOVER);
        imagenNormal = imagenActual;
    }

    public void mostrarEfectoHover() {
        imagenActual = imagenHover;
        repaint();
    }

    public void ocultarEfectoHover() {
        imagenActual = imagenNormal;
        repaint();
    }

    public Bloque getBloque() {
        return miBloque;
    }
}