package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteDefensores;
import logica.entidades.enemigos.Enemigo;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;

import java.awt.geom.Rectangle2D;

public class Escudero extends Defensor {
    protected final Rectangle2D rango;
    protected final int tiempoEquiparEscudo;
    protected int avanceEquiparEscudo;

    protected Escudero(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rango, int salud, int tiempoEquiparEscudo) {
        super(m, salud, b, t,hitbox);
        this.rango = rango;
        this.tiempoEquiparEscudo = tiempoEquiparEscudo;
        avanceEquiparEscudo = 0;
        init();
    }

    public static Escudero create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_ESCUDERO);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ESCUDERO);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ESCUDERO);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_ESCUDERO);
        double alturaRango = alturaHitbox;
        int tiempoEquiparEscudo = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_EQUIPAR_ESCUDO_ESCUDERO);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2 , t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        return new Escudero(m, b, t,hitbox, rango, salud, tiempoEquiparEscudo);
    }

    @Override
    public void actuar(int tiempo) {
        Iterable<Enemigo> enemigos = miTablero.getEnemigosVivos(new Rectangle2D.Double(
                miHitbox.getX() + miHitbox.getWidth(),
                miHitbox.getY(),
                rango.getWidth(),
                rango.getHeight()
        ));
        if(enemigos.iterator().hasNext()){
           //Protegerse con escudo
           avanceEquiparEscudo = Math.min(avanceEquiparEscudo + tiempo, tiempoEquiparEscudo);
           miGrafica.animarHabilidad(avanceEquiparEscudo * 100 / tiempoEquiparEscudo);
        }
        else{
            //Desequipar escudo
            avanceEquiparEscudo = Math.max(avanceEquiparEscudo - tiempo, 0);
            if(avanceEquiparEscudo > 0)
                miGrafica.animarHabilidad(avanceEquiparEscudo * 100 / tiempoEquiparEscudo);
            else
                miGrafica.animarNormal(tiempo);
        }

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
    protected Sonido getSonidoMuerte() {
        return null;
    }
}
