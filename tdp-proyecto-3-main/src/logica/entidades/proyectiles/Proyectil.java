package logica.entidades.proyectiles;

import grafica.entidades.GraficaProyectil;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.Entidad;
import logica.entidades.VisitanteProyectiles;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

public abstract class Proyectil extends Entidad{
    protected final Collection<Bloque> misBloques;
    protected final Motor miMotor;
    protected final GraficaProyectil miGrafica;
    protected final double avancePorSegundo;
    protected final int damageBase;

    protected Proyectil(Motor m, Tablero t, Rectangle2D hitbox, double avancePorSegundo, int damage) {
        super(t, hitbox);
        this.miMotor = m;
        this.miGrafica = crearGraficaProyectil();
        this.avancePorSegundo = avancePorSegundo;
        this.misBloques = new LinkedList<>();
        this.damageBase = damage;

        for (Bloque b : miTablero.obtenerBloques(miHitbox)) {
            b.ocupar(this);
            misBloques.add(b);
        }

        m.agregarProyectil(this); //Debe ser la última línea (sincronización)
    }

    /**
     * Avanza el proyectil a los bloques determinados por Tablero::obtenerBloques()
     * Primero los ocupa, luego comprueba colisiones.
     * Si el proyectil se sale de la ventana, desaparece.
     * @param tiempo tiempo desde el último tick
     */
    @Override
    public void actuar(int tiempo) {
        Iterable<Bloque> bloques;
        double avance = avancePorSegundo * tiempo / 1000;
        miHitbox.setRect(
                miHitbox.getX() + avance,
                miHitbox.getY(),
                miHitbox.getWidth(),
                miHitbox.getHeight());
        miGrafica.animarAvance(tiempo);
        bloques = miTablero.obtenerBloques(miHitbox);
        if(bloques.iterator().hasNext()) {
            for (Bloque b : misBloques) {
                b.desocupar(this);
            }
            misBloques.clear();
            for (Bloque b : bloques) {
                b.ocupar(this);
                misBloques.add(b);
                b.comprobarColisiones(this);
            }
        }
        else
            desaparecer();
    }

    public void desaparecer() {
        miGrafica.desaparecer();
        for(Bloque b: misBloques){
            b.desocupar(this);
        }
        miMotor.eliminarProyectil(this);
    }

    public int getDamage() {
        return damageBase;
    }

    protected abstract GraficaProyectil crearGraficaProyectil();

    public abstract void aceptar(VisitanteProyectiles v);
}