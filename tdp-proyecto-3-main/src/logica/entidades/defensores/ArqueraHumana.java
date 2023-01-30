package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteDefensores;
import logica.entidades.proyectiles.FlechaHumana;
import logica.entidades.proyectiles.Proyectil;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ArqueraHumana extends DefensorDistancia {
    protected ArqueraHumana(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int tiempoAtaque, double alturaAtaque) {
        super(m,b,t,hitbox,rango,salud, tiempoAtaque, alturaAtaque);
    }

    public static ArqueraHumana create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_ARQUERA_HUMANA);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ARQUERA_HUMANA);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ARQUERA_HUMANA);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_ARQUERA_HUMANA);
        double alturaRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_RANGO_ARQUERA_HUMANA);
        double alturaAtaque = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTURA_ATAQUE_ARQUERA_HUMANA);
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_ARQUERA_HUMANA);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2, t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        return new ArqueraHumana(m, b, t,hitbox, salud, rango, tiempoAtaque, alturaAtaque);
    }

    @Override
    public void aceptar(VisitanteDefensores v) {
        v.visitar(this);
    }

    @Override
    protected GraficaDefensor crearGrafica() {
        return GeneradorGraficaDefensores.crearGrafica(this,miTablero.getGrafica());
    }

    @Override
    protected Proyectil crearProyectil(Point2D punto) {
        return FlechaHumana.create(miMotor, miTablero, punto);
    }

    @Override
    protected Sonido getSonidoDisparo() {
        return Sonido.FLECHAZO_HUMANA;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return null;
    }
}