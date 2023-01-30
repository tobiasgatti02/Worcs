package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteDefensores;
import logica.entidades.proyectiles.FlechaElfo;
import logica.entidades.proyectiles.Proyectil;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ArqueroElfo extends DefensorDistancia {
    protected ArqueroElfo(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int tiempoAtaque, double alturaAtaque) {
        super(m,b,t,hitbox,rango,salud, tiempoAtaque, alturaAtaque);
    }

    public static ArqueroElfo create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_ARQUERO_ELFO);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ARQUERO_ELFO);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ARQUERO_ELFO);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_ARQUERO_ELFO);
        double alturaRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_RANGO_ARQUERO_ELFO);
        double alturaAtaque = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTURA_ATAQUE_ARQUERO_ELFO);
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_ARQUERO_ELFO);
        
        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2, t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        return new ArqueroElfo(m, b, t,hitbox, salud, rango, tiempoAtaque, alturaAtaque);
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
        return FlechaElfo.create(miMotor, miTablero, punto);
    }

    @Override
    protected Sonido getSonidoDisparo() {
        return Sonido.FLECHAZO_HUMANA;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return Sonido.MUERTE_DEFENSOR_NO_HADA;
    }
}