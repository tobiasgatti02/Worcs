package logica;

import grafica.GraficaBloque;
import grafica.GraficaTablero;
import grafica.Ventana;

import logica.entidades.defensores.Defensor;
import logica.entidades.enemigos.Enemigo;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import util.config.ConfigDoubleTablero;
import util.config.ConfigIntTablero;
import util.config.LectorConfiguracion;

import java.util.*;

public class Tablero {
    protected final Bloque[][] misBloques;
    protected final GraficaTablero miGrafica;
    protected final Juego miJuego;

    protected final int cantFilas = LectorConfiguracion.getInstance().getConfig(ConfigIntTablero.CANT_FILAS);
    protected final int cantColumnas = LectorConfiguracion.getInstance().getConfig(ConfigIntTablero.CANT_COLUMNAS);
    protected final double ancho = LectorConfiguracion.getInstance().getConfig(ConfigDoubleTablero.ANCHO);
    protected final double alto = LectorConfiguracion.getInstance().getConfig(ConfigDoubleTablero.ALTO);

    public Tablero(Ventana v, Juego juego) {
        miJuego = juego;
        misBloques = new Bloque[cantFilas][cantColumnas];
        miGrafica = new GraficaTablero(this, v);
        Bloque aux;

        for (int i = 0; i < cantFilas; i++)
            for (int j = 0; j < cantColumnas; j++) {
                aux = new Bloque(i, j);
                misBloques[i][j] = aux;
                miGrafica.agregarBloque(aux.getGrafica(), i, j);
            }
    }

    // no garantiza que la columna sea válida
    private int xToColumna(double x) {
        int columna = (int) (x / (ancho/cantColumnas));
        if (x == ancho) columna--; // si la coordenada x coincide con el ancho de tablero se pasa una columna
        return columna;
    }

    // no garantiza que la fila sea válida
    private int yToFila(double y) {
        int fila = (int) (y / (alto/cantFilas));
        if (y == alto) fila--; // si la coordenada y coincide con la altura de tablero se pasa una fila
        return fila;
    }

    public Iterable<Bloque> obtenerBloques(Rectangle2D r) {
        Set<Bloque> toRet = Collections.newSetFromMap(new IdentityHashMap<>());

        int fila = yToFila(r.getY());
        int filaFin = yToFila(r.getY() + r.getHeight());
        int columna = xToColumna(r.getX());
        int columnaFin = xToColumna(r.getX() + r.getWidth());

        for (int f = fila; f >= 0 && f < cantFilas && f <= filaFin; f++)
            for (int c = columna; c >= 0 && c < cantColumnas && c <= columnaFin; c++)
                toRet.add(misBloques[f][c]);

        return toRet;
    }

    public Iterable<Enemigo> getEnemigosVivos(Rectangle2D r) {
        Set<Enemigo> toRet = Collections.newSetFromMap(new IdentityHashMap<>());
        Iterable<Bloque> bloques = obtenerBloques(r);

        for (Bloque bloque:bloques)
            for (Enemigo enemigo:bloque.getEnemigosVivos(r)) {
                if (enemigo.getHitbox().intersects(r))
                    toRet.add(enemigo);
            }

        return toRet;
    }

    public Iterable<Defensor> getDefensores(Rectangle2D r) {
        Set<Defensor> toRet = Collections.newSetFromMap(new IdentityHashMap<>());
        Iterable<Bloque> bloques = obtenerBloques(r);
        Defensor aux;

        for (Bloque bloque:bloques) {
            aux = bloque.getDefensor();
            if(aux != null && aux.getHitbox().intersects(r))
                toRet.add(aux);
        }

        return toRet;
    }

    // dado un rectangulo r en el sistema de coordenadas origen,
    // lo mapea a uno equivalente en el sistema de coordenadas destino
    public Rectangle2D mapear(Rectangle2D r, Rectangle2D coordenadasDestino) {
        double x = Math.round((r.getX() / ancho) * coordenadasDestino.getWidth());
        double y = Math.round((r.getY() / alto) * coordenadasDestino.getHeight());
        double width = Math.round((r.getWidth() / ancho) * coordenadasDestino.getWidth());
        double height = Math.round((r.getHeight() / alto) * coordenadasDestino.getHeight());

        return new Rectangle2D.Double(x + coordenadasDestino.getX(), y + coordenadasDestino.getY(), width, height);
    }

    public Rectangle2D getFila(int f) {
        double altoFila = (alto/cantFilas);
        return new Rectangle2D.Double(0, altoFila * f, ancho, altoFila);
    }

    public Rectangle2D getBoundsBloque(Bloque b) {
        // crear Rectangle de un bloque dado
        double width = ancho/cantColumnas;
        double height = alto/cantFilas;
        double x = width * b.getColumna();
        double y = height * b.getFila();

        return new Rectangle2D.Double(x, y, width, height);
    }

    public Point2D mapear(Point2D p, Rectangle2D coordenadasDestino) {
        double x = Math.round((p.getX() / ancho) * coordenadasDestino.getWidth());
        double y =  Math.round((p.getY() / alto) * coordenadasDestino.getHeight());

        return new Point2D.Double(x, y);
    }

    public int getCantFilas() {
        return cantFilas;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public Juego getJuego() {
        return miJuego;
    }

    public GraficaTablero getGrafica() {
        return miGrafica;
    }

    public void destruirGrafica() {
        Collection<GraficaBloque> bloquesAEliminar = new ArrayList<>(cantFilas * cantColumnas);
        for (int i = 0; i < cantFilas; i++)
            for (int j = 0; j < cantColumnas; j++)
                bloquesAEliminar.add(misBloques[i][j].getGrafica());
        miGrafica.destruir(bloquesAEliminar);
    }
}