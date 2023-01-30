package logica;

import grafica.GraficaBloque;

import logica.entidades.defensores.Defensor;
import logica.entidades.enemigos.Enemigo;
import logica.entidades.proyectiles.Proyectil;

import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;

public class Bloque {
    protected final GraficaBloque miGrafica;
    protected Defensor miDefensor;
    protected final List<Enemigo> misEnemigos;
    protected final List<Proyectil> misProyectiles;
    protected final Collection<Enemigo> enemigosAEliminar;
    protected final Collection<Proyectil> proyectilesAEliminar;
    protected final int fila;
    protected final int columna;

    protected boolean iterando;

    public Bloque(int fila, int columna) {
        miGrafica = new GraficaBloque(this, fila, columna);
        misEnemigos = new LinkedList<>();
        misProyectiles = new LinkedList<>();
        this.fila = fila;
        this.columna = columna;
        enemigosAEliminar = Collections.newSetFromMap(new IdentityHashMap<>());
        proyectilesAEliminar = Collections.newSetFromMap(new IdentityHashMap<>());
        iterando = false;
    }

    public void comprobarColisiones(Proyectil p) {
        iterando = true;
        for (Enemigo enemigo:misEnemigos)
            if (enemigo.getHitbox().intersects(p.getHitbox()))
                p.aceptar(enemigo);
        iterando = false;
        procesarEliminaciones();
    }

    public void comprobarColisiones(Enemigo e) {
        iterando = true;
        for (Proyectil proyectil:misProyectiles)
            if (proyectil.getHitbox().intersects(e.getHitbox()))
                proyectil.aceptar(e);
        iterando = false;
        procesarEliminaciones();
    }

    private void procesarEliminaciones() {
        for (Enemigo e : enemigosAEliminar) {
            misEnemigos.remove(e);
        }
        enemigosAEliminar.clear();
        for (Proyectil p : proyectilesAEliminar) {
            misProyectiles.remove(p);
        }
        proyectilesAEliminar.clear();
    }

    public void ocupar(Defensor d) {
        if (miDefensor == null)
            miDefensor = d;
    }

    public void ocupar(Enemigo e) {
        misEnemigos.add(e);
    }

    public void ocupar(Proyectil p) {
        misProyectiles.add(p);
    }

    public void desocupar(Defensor d) {
        if (miDefensor == d)
            miDefensor = null;
    }

    public void desocupar(Enemigo e) {
        if(iterando)
            enemigosAEliminar.add(e);
        else
            misEnemigos.remove(e);
    }

    public void desocupar(Proyectil p) {
        if (iterando)
            proyectilesAEliminar.add(p);
        else
            misProyectiles.remove(p);
    }

    public Iterable<Enemigo> getEnemigosVivos(Rectangle2D r) {
        List<Enemigo> toRet = new LinkedList<>();

        for (Enemigo enemigo:misEnemigos)
            if (r.intersects(enemigo.getHitbox()))
                toRet.add(enemigo);

        return toRet;
    }

    public Defensor getDefensor() {
        return miDefensor;
    }

    public GraficaBloque getGrafica() {
        return miGrafica;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}