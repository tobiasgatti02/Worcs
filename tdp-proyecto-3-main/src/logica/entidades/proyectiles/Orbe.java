package logica.entidades.proyectiles;

import grafica.entidades.GeneradorGraficaProyectiles;
import grafica.entidades.GraficaProyectil;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteProyectiles;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Orbe extends Proyectil {
    protected Orbe(Motor m, Tablero t, Rectangle2D hitbox, double avancePorSegundo, int damage) {
        super(m, t, hitbox, avancePorSegundo, damage);
    }

    public static Orbe create(Motor m, Tablero t, Point2D posicionInicial){
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ORBE);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ORBE);

        Rectangle2D hitbox = new Rectangle2D.Double(
                posicionInicial.getX(),
                posicionInicial.getY(),
                anchoHitbox,
                alturaHitbox);

        double avancePorSegundo = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.AVANCE_POR_SEGUNDO_ORBE);
        int damage = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DAMAGE_ORBE);

        return new Orbe(m, t, hitbox, avancePorSegundo, damage);
    }
    @Override
    public GraficaProyectil crearGraficaProyectil() {
        return GeneradorGraficaProyectiles.crearGrafica(this, miTablero.getGrafica());
    }

    @Override
    public void aceptar(VisitanteProyectiles v) {
        v.visitar(this);
    }

    public double getRealentizacion() {
        return LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.REALENTIZACION_ORBE);
    }

    public int getDuracionEfecto() {
        return LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DURACION_EFECTO_ORBE);
    }
}