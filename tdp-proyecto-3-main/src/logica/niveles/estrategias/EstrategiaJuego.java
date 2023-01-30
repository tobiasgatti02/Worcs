package logica.niveles.estrategias;

import logica.Juego;
import logica.Tablero;
import logica.niveles.FabricaEnemigos;
import util.config.ConfigDoubleNiveles;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EstrategiaJuego {
    protected final Juego miJuego;
    protected final FabricaEnemigos miFabrica;

    private final List<Integer> hordas;  //Los valores son los enemigos necesarios para crear cada horda
    protected final List<Integer> enemigosRestantes;
    protected final int cantEnemigosTotales;
    protected final AtomicInteger cantEnemigosCreados;
    protected final double probabilidadPareja;

    protected final int tiempoTotalEstimado;

    protected int tiempoDesdeEnemigo;
    protected final int tiempoHastaEnemigo;

    protected final int cooldownHorda;
    protected final int tiempoNotificarHorda;
    protected boolean hordaNotificada;
    private int tiempoDesdeNotificacion;
    protected final AtomicInteger hordasCreadas;

    protected final Random rand;

    public EstrategiaJuego(
            Juego j,
            FabricaEnemigos f,
            int cantEnemigo1,
            int cantEnemigo2,
            int cantEnemigo3,
            double probabilidadPareja,
            int tiempoEntreEnemigos,
            int tiempoNotificarHorda
    )
    {
        this.miJuego = j;
        this.miFabrica = f;
        this.enemigosRestantes = new ArrayList<>();
        enemigosRestantes.add(cantEnemigo1);
        enemigosRestantes.add(cantEnemigo2);
        enemigosRestantes.add(cantEnemigo3);
        this.cantEnemigosTotales = cantEnemigo1 + cantEnemigo2 + cantEnemigo3;
        cantEnemigosCreados = new AtomicInteger();
        this.cantEnemigosCreados.set(0);
        this.hordas = getHordas();
        this.hordasCreadas = new AtomicInteger(0);
        this.cooldownHorda = LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.COOLDOWN_HORDA);
        this.hordaNotificada = false;
        this.tiempoDesdeNotificacion = 0;
        this.tiempoNotificarHorda = tiempoNotificarHorda;
        this.probabilidadPareja = probabilidadPareja;
        this.tiempoDesdeEnemigo = (int) (tiempoEntreEnemigos * (-LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.TIEMPO_PRIMER_ENEMIGO)));
        this.tiempoHastaEnemigo = tiempoEntreEnemigos;
        this.tiempoTotalEstimado = tiempoRestanteEstimado();
        this.rand = new Random();
    }

    public void avanzar(int tiempo) {
        tiempoDesdeEnemigo += tiempo;

        if(hordasCreadas.get() < hordas.size() && cantEnemigosCreados.get() >= hordas.get(hordasCreadas.get())){
            if (!hordaNotificada) {
                miJuego.notificarHorda();
                hordaNotificada = true;
            } else if (tiempoDesdeNotificacion >= tiempoNotificarHorda) {
                tiempoDesdeNotificacion = 0;
                hordaNotificada = false;
                tiempoDesdeEnemigo -= tiempoHastaEnemigo * cooldownHorda;
                crearHorda(hordasCreadas.get() + 1);
                hordasCreadas.incrementAndGet(); //Debe actualizarse después de crear la horda
            } else {
                tiempoDesdeNotificacion += tiempo;
            }
        }

        if (getEnemigosRestantes() > 0 && tiempoDesdeEnemigo >= tiempoHastaEnemigo) {
            tiempoDesdeEnemigo -= tiempoHastaEnemigo;
            crearEnemigosNormales();
        }
    }

    public boolean quedanEnemigos() {
        return hordasCreadas.get() < hordas.size() || cantEnemigosCreados.get() < cantEnemigosTotales;
    }

    protected void crearEnemigo(int tipo, int fila, double posX) {
        Tablero tablero = miJuego.getTablero();
        double y = getAbajoFila(fila);
        Point2D point = new Point2D.Double(posX, y);

        switch (tipo) {
            case 1 -> miFabrica.crearEnemigo1(tablero.getJuego().getMotor(), point, tablero);
            case 2 -> miFabrica.crearEnemigo2(tablero.getJuego().getMotor(), point, tablero);
            case 3 -> miFabrica.crearEnemigo3(tablero.getJuego().getMotor(), point, tablero);
        }
    }

    protected double getAbajoFila(int fila) {
        Tablero tablero = miJuego.getTablero();
        Rectangle2D recFila = tablero.getFila(fila);
        return recFila.getY() + recFila.getHeight()-1;
    }

    protected int getEnemigosRestantes() {
        return cantEnemigosTotales - cantEnemigosCreados.get();
    }

    private void crearEnemigosNormales() {
        int tipo1;
        int tipo2;
        int fila1 = rand.nextInt(miJuego.getTablero().getCantFilas());
        int fila2;
        final double posX = miJuego.getTablero().getAncho()*1.1;
        tipo1 = sortearTipoEnemigo();
        crearEnemigo(tipo1, fila1, posX);
        enemigosRestantes.set(tipo1-1, enemigosRestantes.get(tipo1-1) - 1);
        cantEnemigosCreados.getAndIncrement(); //Debe incrementarse después de crear el enemigo.

        boolean pareja = rand.nextDouble() <= probabilidadPareja;
        if (getEnemigosRestantes() >= 2 && pareja) {
            tipo2 = sortearTipoEnemigo();
            enemigosRestantes.set(tipo2-1, enemigosRestantes.get(tipo2-1) - 1);
            cantEnemigosCreados.getAndIncrement(); //Debe incrementarse después de crear el enemigo.
            do {
                fila2 = rand.nextInt(miJuego.getTablero().getCantFilas());
            } while (fila1 == fila2);

            crearEnemigo(tipo2, fila2,posX);
        }
    }

    private int sortearTipoEnemigo() {
        int i;
        do {
            i = rand.nextInt(3);
        } while (enemigosRestantes.get(i) == 0);
        return i + 1;
    }

    public float getPorcentajeAvance() {
        return 100 - (tiempoRestanteEstimado() * 100.0f / tiempoTotalEstimado);
    }

    public int tiempoRestanteEstimado() {
        int tiempoProxEnemigo = Math.max(0, tiempoHastaEnemigo - tiempoDesdeEnemigo);
        int toReturn;
        int enemigosRestantes = getEnemigosRestantes();
        if(enemigosRestantes > 0)
            toReturn = tiempoProxEnemigo +  tiempoHastaEnemigo * (enemigosRestantes - 1);
        else
            toReturn = 0;
        return toReturn;
    }

    public List<Float> getPorcentajesHordas(){
        List<Float> toReturn = new ArrayList<>();
        for (Integer i : hordas) {
            toReturn.add(i * 100.0f / cantEnemigosTotales);
        }
        return toReturn;
    }

    protected abstract void crearHorda(int horda);
    protected abstract List<Integer> getHordas();
}