package logica.entidades.defensores;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.enemigos.Enemigo;
import logica.entidades.proyectiles.Proyectil;
import util.Sonido;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class DefensorDistancia extends DefensorCombatiente{
    protected DefensorDistancia(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rangoAtaque,int salud, int tiempoAtaque, double alturaAtaque) {
        super(m,salud,b,t,hitbox,rangoAtaque, tiempoAtaque, alturaAtaque);
        //grafico se inicializa en el constructor de las subclases
    }

    protected abstract Proyectil crearProyectil(Point2D punto);

    @Override
    protected void atacar(Iterable<Enemigo> enemigos) {
        reproducirSonido(getSonidoDisparo());
        crearProyectil(new Point2D.Double(miHitbox.getCenterX(), alturaAtaque));
    }
    protected abstract Sonido getSonidoDisparo();
}