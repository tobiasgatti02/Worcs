package grafica.entidades;

import grafica.GraficaTablero;

public abstract class GraficaDefensor extends GraficaEntidad {
    private final AnimacionPorcentaje animacionHabilidad;
    private final AnimacionTemporizada animacionNormal;
    public GraficaDefensor(GraficaTablero g, AnimacionPorcentaje habilidad, AnimacionTemporizada normal) {
        super(g, normal);
        animacionHabilidad = habilidad;
        animacionNormal = normal;
    }
    @Override
    protected void init(){
        super.init();
        animacionActual = animacionNormal;
        repaint();
        miTablero.agregarEntidad(this);
    }
    public void animarHabilidad(int porcentaje) {
        animacionActual = animacionHabilidad;
        animacionHabilidad.setPorcentaje(porcentaje);
        actualizarAnimacion();
    }

    public void animarNormal(int tiempo) {
        animacionActual = animacionNormal;
        animacionNormal.avanzar(tiempo);
        actualizarAnimacion();
    }
}