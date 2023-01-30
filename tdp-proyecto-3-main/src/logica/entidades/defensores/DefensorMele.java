package logica.entidades.defensores;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteEnemigos;
import logica.entidades.enemigos.*;
import util.Sonido;
import java.awt.geom.Rectangle2D;

public abstract class DefensorMele extends DefensorCombatiente implements VisitanteEnemigos {
    protected final int damage;

    protected DefensorMele(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rangoAtaque, int salud, int damage, int tiempoAtaque) {
        super(m, salud, b, t, hitbox, rangoAtaque, tiempoAtaque, hitbox.getHeight());
        //grafico se inicializa en el constructor de las subclases
        this.damage = damage;
    }

    @Override
    protected void atacar(Iterable<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos) {
            enemigo.aceptar(this);
        }
        reproducirSonido(getSonidoImpacto());
    }

    @Override
    public void visitar(OrcoMazo e) {
        e.daniar(damage);
    }

    @Override
    public void visitar(OrcoEspada e) {
        e.daniar(damage);
    }

    @Override
    public void visitar(OrcoHacha e) {
        e.daniar(damage);
    }

    @Override
    public void visitar(TrollTronco e) {
        e.daniar(damage);
    }

    @Override
    public void visitar(TrollHueso e) {
       e.daniar(damage);
    }

    @Override
    public void visitar(TrollMazoPiedra e) {
       e.daniar(damage);
    }

    protected abstract Sonido getSonidoImpacto();
    public Rectangle2D getRango(){
            return rango;
    }
}