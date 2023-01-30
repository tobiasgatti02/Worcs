package logica.entidades.defensores;

import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.enemigos.Enemigo;
import java.awt.geom.Rectangle2D;

public abstract class DefensorCombatiente extends Defensor {
    protected final Rectangle2D rango;
    protected final double alturaAtaque;
    protected final int tiempoAtaque;
    protected int tiempoDesdeInicioAtaque;
    protected boolean atacado;

    protected DefensorCombatiente(Motor m, int salud, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rango, int tiempoAtaque, double alturaAtaque) {
        super(m, salud, b, t, hitbox);
        this.rango = rango;

        this.alturaAtaque = miHitbox.getY() + miHitbox.getHeight() - alturaAtaque;
        this.tiempoAtaque = tiempoAtaque;
        tiempoDesdeInicioAtaque = 0;
        atacado = false;
        init();
    }

    @Override
    public void actuar(int tiempo) {
        Iterable<Enemigo> enemigos = miTablero.getEnemigosVivos(new Rectangle2D.Double(
                miHitbox.getX() + miHitbox.getWidth(),
                alturaAtaque,
                rango.getWidth(),
                rango.getHeight()
        ));

        if (enemigos.iterator().hasNext()) {
            tiempoDesdeInicioAtaque += tiempo;
            miGrafica.animarHabilidad(tiempoDesdeInicioAtaque * 100 / tiempoAtaque);
            //Ataca la primera vez que supera el 50% del tiempo de ataque
            if(!atacado && tiempoDesdeInicioAtaque >= tiempoAtaque / 2) {
                atacar(enemigos);
                atacado = true;
            }

            if (tiempoDesdeInicioAtaque >= tiempoAtaque) { //Reiniciar ataque
                tiempoDesdeInicioAtaque -= tiempoAtaque;
                atacado = false;
            }
            miGrafica.animarHabilidad(tiempoDesdeInicioAtaque * 100 / tiempoAtaque);

        } else {
            tiempoDesdeInicioAtaque = Math.min(tiempoAtaque, tiempoDesdeInicioAtaque + tiempo);
            miGrafica.animarNormal(tiempo);
        }
    }
    protected abstract void atacar(Iterable<Enemigo> enemigos);
}
