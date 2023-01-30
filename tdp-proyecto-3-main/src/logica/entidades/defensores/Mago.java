package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteDefensores;
import logica.entidades.proyectiles.Orbe;
import logica.entidades.proyectiles.Proyectil;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Mago extends DefensorDistancia {
    protected Mago(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int tiempoAtaque, double alturaAtaque) {
        super(m,b,t,hitbox,rango,salud, tiempoAtaque, alturaAtaque);
    }

    public static Mago create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_MAGO);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_MAGO);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_MAGO);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_MAGO);
        double alturaRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_RANGO_MAGO);
        double alturaAtaque = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTURA_ATAQUE_MAGO);
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_MAGO);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2, t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        return new Mago(m, b, t,hitbox, salud, rango, tiempoAtaque, alturaAtaque);
    }

    @Override
    public void aceptar(VisitanteDefensores v) {
        v.visitar(this);
    }

    @Override
    protected GraficaDefensor crearGrafica() {
        return GeneradorGraficaDefensores.crearGrafica(this, miTablero.getGrafica());
    }

    @Override
    protected Proyectil crearProyectil(Point2D punto) {
        return Orbe.create(miMotor, miTablero, punto);
    }

    @Override
    protected Sonido getSonidoDisparo() {
        return Sonido.DISPARO_MAGO;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return null;
    }
}