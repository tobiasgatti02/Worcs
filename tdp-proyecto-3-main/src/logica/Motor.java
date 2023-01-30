package logica;
import logica.entidades.defensores.Defensor;
import logica.entidades.enemigos.Enemigo;
import logica.entidades.proyectiles.Proyectil;
import util.config.ConfigIntTicks;
import util.config.LectorConfiguracion;

import java.util.*;

public class Motor implements Runnable {
    protected final Thread miHilo;
    private final Juego miJuego;
    private final int delayEnemigos;
    private final int delayDefensores;
    private final int delayProyectiles;
    private volatile boolean parar;
    protected final Collection<Defensor> defensores;
    protected final Collection<Proyectil> proyectiles;
    protected final Collection<Enemigo> enemigos;

    protected final Collection<Enemigo> enemigosAEliminar;
    protected final Collection<Defensor> defensoresAEliminar;
    protected final Collection<Proyectil> proyectilesAEliminar;

    protected final Collection<Enemigo> enemigosAAgregar;
    protected final Collection<Defensor> defensoresAAgregar;
    protected final Collection<Proyectil> proyectilesAAgregar;
    private long ticksDefensores;
    private long ticksEnemigos;
    private long ticksProyectiles;


    public Motor(Juego j) {
        miJuego = j;
        defensores = new LinkedList<>();
        proyectiles = new LinkedList<>();
        enemigos = new LinkedList<>();
        miHilo = new Thread(this);

        enemigosAEliminar = Collections.newSetFromMap(new IdentityHashMap<>());
        defensoresAEliminar = Collections.newSetFromMap(new IdentityHashMap<>());
        proyectilesAEliminar = Collections.newSetFromMap(new IdentityHashMap<>());

        enemigosAAgregar = new ArrayList<>();
        defensoresAAgregar = new ArrayList<>();
        proyectilesAAgregar = new ArrayList<>();
        ticksDefensores = 0;
        ticksEnemigos = 0;
        ticksProyectiles = 0;
        delayEnemigos = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.DELAY_ENEMIGOS);
        delayDefensores = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.DELAY_DEFENSORES);
        delayProyectiles = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.DELAY_PROYECTILES);
    }

    public void iniciar(){
        miHilo.start();
    }
    @Override
    public void run() {
        final int porcentajeVelocidad = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.PORCENTAJE_VELOCIDAD);
        final int tiempoTick = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.TIEMPO_POR_TICK_MOTOR);
        final int delayTick = tiempoTick * 100 / porcentajeVelocidad;

        while (!parar){
            recorrerEntidades(tiempoTick);

            procesarAgregados();
            procesarEliminaciones();

            if(enemigos.isEmpty()){
                synchronized (enemigosAAgregar){
                    if(enemigosAAgregar.isEmpty()){
                        miJuego.sinEnemigosVivos();
                    }
                }
            }
            try {
                Thread.sleep(delayTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Limpiar el tablero luego de parar
        desaparecerGraficas();
    }

    private void recorrerEntidades(int tiempoTick) {
        if(ticksEnemigos == delayEnemigos - 1) {
            recorrerEnemigos(delayEnemigos * tiempoTick);
            ticksEnemigos = 0;
        }
        else
            ticksEnemigos++;

        if(ticksDefensores == delayDefensores - 1) {
            recorrerDefensores(delayDefensores * tiempoTick);
            ticksDefensores = 0;
        }
        else
            ticksDefensores++;

        if(ticksProyectiles == delayProyectiles - 1) {
            recorrerProyectiles(delayProyectiles * tiempoTick);
            ticksProyectiles = 0;
        }
        else
            ticksProyectiles++;
    }

    protected void recorrerEnemigos(int tiempo){
        for(Enemigo e: enemigos)
            if(!enemigosAEliminar.contains(e))
                e.actuar(tiempo);
    }
    protected void recorrerProyectiles(int tiempo){
        for(Proyectil p: proyectiles)
            if(!proyectilesAEliminar.contains(p))
                p.actuar(tiempo);
    }
    protected void recorrerDefensores(int tiempo){
        for(Defensor d: defensores)
            if(!defensoresAEliminar.contains(d))
                d.actuar(tiempo);
    }

    protected synchronized void procesarAgregados(){
        enemigos.addAll(enemigosAAgregar);
        enemigosAAgregar.clear();

        defensores.addAll(defensoresAAgregar);
        defensoresAAgregar.clear();

        proyectiles.addAll(proyectilesAAgregar);
        proyectilesAAgregar.clear();
    }
    protected void procesarEliminaciones(){
        enemigos.removeAll(enemigosAEliminar);
        enemigosAEliminar.clear();

        defensores.removeAll(defensoresAEliminar);
        defensoresAEliminar.clear();

        proyectiles.removeAll(proyectilesAEliminar);
        proyectilesAEliminar.clear();
    }

    public void parar() {
        parar = true;
    }

    public synchronized void agregarProyectil(Proyectil p) {
        proyectilesAAgregar.add(p);
    }

    public synchronized void agregarEnemigo(Enemigo e) {
        synchronized (enemigosAAgregar){
            enemigosAAgregar.add(e);
        }
    }

    public synchronized void agregarDefensor(Defensor d) {
        defensoresAAgregar.add(d);
    }

    public void eliminarEnemigo(Enemigo e) {
        enemigosAEliminar.add(e);
    }

    public void eliminarProyectil(Proyectil p) {
        proyectilesAEliminar.add(p);
    }

    public void eliminarDefensor(Defensor d) {
        defensoresAEliminar.add(d);
    }

    protected void desaparecerGraficas() {
        for (Enemigo e : enemigos) {
            e.desaparecer();
        }
        for (Defensor d : defensores) {
            d.desaparecer();
        }
        for (Proyectil p : proyectiles) {
            p.desaparecer();
        }
    }
}