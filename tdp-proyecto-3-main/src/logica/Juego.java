package logica;
import grafica.Ventana;
import logica.cartas.Carta;
import logica.entidades.defensores.Defensor;
import logica.niveles.GeneradorNiveles;
import logica.niveles.Nivel;
import logica.niveles.estrategias.*;
import util.ReproductorSonidos;
import util.Sonido;
import util.config.ConfigIntTicks;
import util.config.LectorConfiguracion;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class Juego {
    private final AtomicBoolean parar;
    protected int monedas;//cantidad de monedas disponibles del jugador
    protected final Thread miHiloCartas;
    protected final Thread miHiloEstrategia;
    protected final Tablero miTablero;
    protected final Ventana miVentana;
    protected final List<Moneda> misMonedas;//monedas disponibles en pantalla
    protected final EstrategiaJuego miEstrategia;
    protected final ModoJuego modo;
    protected final Nivel nivelActual;
    protected final Motor miMotor;
    protected final Iterable<Carta> misCartas;


    public Juego(Ventana v, int n, ModoJuego m) {
        parar = new AtomicBoolean();
        parar.set(false);
        misMonedas = new ArrayList<>();
        monedas = 0;
        miVentana = v;
        modo = m;
        miMotor = new Motor(this);
        miTablero = new Tablero(miVentana, this);
        nivelActual = GeneradorNiveles.getInstance().generarNivel(n, modo,this);
        misCartas = nivelActual.getFabricaCartas().crearCartas();
        for (Carta c : misCartas) {
            miVentana.agregarCarta(c.getGrafica());
        }
        aumentarMonedas(nivelActual.getMonedasIniciales());
        miEstrategia = nivelActual.getEstrategia();
        miVentana.actualizarNivel(nivelActual.getNumero(), miEstrategia.getPorcentajesHordas());
        miHiloCartas = new Thread(this::procesarCartas);

        miHiloEstrategia = new Thread(this::avanzarEstrategias);
        miVentana.mostrarCuentaAtras(modo).thenRun(()->{
            miHiloEstrategia.start();
            miHiloCartas.start();
            miMotor.iniciar();
        });
    }

    public void parar() {
        parar.set(true);
        miMotor.parar();
        miVentana.vaciarCartas();
        miTablero.destruirGrafica();
        for (Moneda m: misMonedas)
            m.desaparecer();
    }

    public synchronized void gameOver(){
        if(!parar.get()) {
            parar();
            miVentana.gameOver();
        }
    }
    public void notificarHorda() {
        miVentana.notificarHorda();
    }

    public void terminarNivel() {
        parar();
        miVentana.nivelTerminado(modo, nivelActual.getNumero());
    }

    public void usarCarta(Bloque b, Carta c) {
        if(b.getDefensor() == null && c.estaDisponible() && monedas >= c.getPrecio()){
            ReproductorSonidos.getInstance().reproducirSFX(Sonido.DEFENSOR_COLOCADO);
            c.usar(miMotor , b, miTablero);
            disminuirMonedas(c.getPrecio());
            actualizarMonedasVentana();
        }
    }

    //Requiere monedas >= precio
    public void disminuirMonedas(int precio) {
        monedas -= precio;
        actualizarDisponibilidadCartas();
    }

    public void aumentarMonedas(int n) {
        monedas += n;
        actualizarMonedasVentana();
        actualizarDisponibilidadCartas();
    }

    private void actualizarDisponibilidadCartas() {
        for (Carta c : misCartas) {
            c.comprobarDisponibilidad(monedas);
        }
    }

    public void crearMoneda(Point2D p) {
        Moneda aInsertar = new Moneda(p,this,miTablero);
        misMonedas.add(aInsertar);
    }

    public void eliminarMoneda(Moneda d) {
        if(!parar.get())
            misMonedas.remove(d);
    }

    public Tablero getTablero() {
        return miTablero;
    }
    public Motor getMotor() {
        return miMotor;
    }
    public void usarPala(Bloque b) {
        Defensor def = b.getDefensor();
        if (def != null){
            def.desaparecer();
        }
    }
    private void actualizarMonedasVentana() {
        miVentana.setMonedas(monedas);
    }
    private void procesarCartas() {
        //Corre en un hilo dedicado
        final int porcentajeVelocidad = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.PORCENTAJE_VELOCIDAD);
        final int tiempoTick = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.TIEMPO_POR_TICK_CARTAS);
        final int delayTick = tiempoTick * 100 / porcentajeVelocidad;
        while (!parar.get()) {
            actualizarCooldownCartas(tiempoTick);
            try {
                Thread.sleep(delayTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarCooldownCartas(int tiempo) {
        for(Carta c: misCartas){
            c.actualizarCooldown(tiempo);
        }
    }

    private void avanzarEstrategias() {
        //Corre en un hilo dedicado
        final int porcentajeVelocidad = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.PORCENTAJE_VELOCIDAD);
        final int tiempoTick = LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.TIEMPO_POR_TICK_ESTRATEGIAS);
        final int delayTick = tiempoTick * 100 / porcentajeVelocidad;
        while (!parar.get()) {
            miEstrategia.avanzar(tiempoTick);
            miVentana.actualizarProgreso(miEstrategia.getPorcentajeAvance());
            try {
                Thread.sleep(delayTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sinEnemigosVivos() {
        if(!miEstrategia.quedanEnemigos())
            terminarNivel();
    }
}