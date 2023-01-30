package logica.entidades.enemigos;

import grafica.entidades.GraficaEnemigo;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.Entidad;
import logica.entidades.VisitanteDefensores;
import logica.entidades.VisitanteEnemigos;
import logica.entidades.VisitanteProyectiles;
import logica.entidades.defensores.*;
import logica.entidades.proyectiles.FlechaElfo;
import logica.entidades.proyectiles.FlechaHumana;
import logica.entidades.proyectiles.Orbe;
import util.*;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Asegura salud >= 0
 */
public abstract class Enemigo extends Entidad implements VisitanteProyectiles, VisitanteDefensores {
    protected int salud;
    protected final Motor miMotor;
    protected final GraficaEnemigo miGrafica;
    protected final Collection<Bloque> misBloques;
    protected final Rectangle2D rangoAtaque;
    protected final int damage;
    protected final int tiempoMuerte;
    protected final int tiempoAtaque;
    protected final double avancePorSegundo;
    protected double fraccionVelocidad; // Permite efecto de lentitud <=1
    protected int tiempoHastaResetVelocidad;
    private EstadoEnemigo estado;

    public Enemigo(Motor m, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int damage, double avancePorSegundo, int tiempoAtaque, int tiempoMuerte) {
        super(t, hitbox);
        //grafico se inicializa en el constructor de las subclases
        miMotor = m;
        rangoAtaque = rango;

        miGrafica = crearGrafica();

        this.salud = salud;
        this.damage = damage;
        this.avancePorSegundo = avancePorSegundo;

        misBloques = new LinkedList<>();

        this.tiempoMuerte = tiempoMuerte;
        this.tiempoAtaque = tiempoAtaque;
        for (Bloque b : miTablero.obtenerBloques(miHitbox)) {
            b.ocupar(this);
            misBloques.add(b);
        }
        fraccionVelocidad = 1;

        estado = new EnemigoAvanzando(this);
        miMotor.agregarEnemigo(this); //Debe ser la última línea (sincronización)

    }

    protected void setFraccionVelocidad(double fraccion, int tiempo){
        fraccionVelocidad = Math.max(fraccion, 0);
        tiempoHastaResetVelocidad = tiempo;
    }

    @Override
    public void actuar(int tiempo){
        if(salud>0){
            if(tiempoHastaResetVelocidad > 0){
                tiempoHastaResetVelocidad = Math.max(tiempoHastaResetVelocidad - tiempo, 0);
                if(tiempoHastaResetVelocidad == 0)
                    fraccionVelocidad = 1;
            }
        }
        estado.actuar(tiempo);

    }

    private Iterable<Defensor> getDefensoresEnRango() {
        return miTablero.getDefensores(new Rectangle2D.Double(
                miHitbox.getX() - rangoAtaque.getWidth(),
                miHitbox.getY(),
                rangoAtaque.getWidth(),
                rangoAtaque.getHeight()
        ));
    }

    protected class EnemigoAvanzando implements EstadoEnemigo{
        protected final Enemigo miEnemigo;
        public EnemigoAvanzando(Enemigo e){
            miEnemigo = e;
        }
        @Override
        public void actuar(int tiempo) {
            double avance = avancePorSegundo * tiempo / 1000 * fraccionVelocidad;
            if(!getDefensoresEnRango().iterator().hasNext()) {
                //Avanzar
                miHitbox.setRect(
                        miHitbox.getX() - avance,
                        miHitbox.getY(),
                        miHitbox.getWidth(),
                        miHitbox.getHeight());

                miGrafica.animarAvance(avance);

                Iterable<Bloque> bloquesNuevos = miTablero.obtenerBloques(miHitbox);

                if (!misBloques.isEmpty() && !bloquesNuevos.iterator().hasNext()) { //Si el enemigo deja de tener bloques (sale del tablero)
                    miTablero.getJuego().gameOver();
                } else {
                    //Cambiar bloques ocupados y comprobar colisiones
                    for (Bloque b : misBloques) {
                        b.desocupar(miEnemigo);
                    }
                    misBloques.clear();
                    for (Bloque b : bloquesNuevos) {
                        b.ocupar(miEnemigo);
                        misBloques.add(b);
                    }
                    for (Bloque b : misBloques) {
                        b.comprobarColisiones(miEnemigo);
                    }
                }
            }
            else {
                estado = new EnemigoAtacando(miEnemigo);
            }
        }
    }

    protected class EnemigoAtacando implements EstadoEnemigo{
        protected int tiempoDesdeInicioAtaque;
        protected boolean atacado;

        protected final Enemigo miEnemigo;
        public EnemigoAtacando(Enemigo e){
            miEnemigo = e;
            tiempoDesdeInicioAtaque = 0;
            atacado = false;
        }
        @Override
        public void actuar(int tiempo) {
            Iterable<Defensor> defensores = getDefensoresEnRango();
            if (!defensores.iterator().hasNext()) {
                estado = new EnemigoAvanzando(miEnemigo);
            } else {
                //Ataca la primera vez que supera el 50% del tiempo de ataque
                if(tiempoDesdeInicioAtaque >= tiempoAtaque / 2 && !atacado) {
                    for (Defensor d : defensores) {
                        d.aceptar(miEnemigo);
                    }
                    atacado = true;
                    reproducirSonido(getSonidoImpacto());
                }
                tiempoDesdeInicioAtaque += tiempo * fraccionVelocidad;
                if (tiempoDesdeInicioAtaque >= tiempoAtaque) { //Reiniciar ataque
                    tiempoDesdeInicioAtaque -= tiempoAtaque;
                    atacado = false;
                }
                miGrafica.animarAtaque(tiempoDesdeInicioAtaque * 100 / tiempoAtaque);
            }
        }
    }

    protected class EnemigoMuriendo implements EstadoEnemigo{
        protected int tiempoDesdeMuerte;

        public EnemigoMuriendo(Enemigo e){
            reproducirSonido(getSonidoMuerte());
        }
        @Override
        public void actuar(int tiempo) {
            tiempoDesdeMuerte += tiempo;
            miGrafica.animarMuerte(Math.min(tiempoDesdeMuerte * 100 / tiempoMuerte, 100));
            if(tiempoDesdeMuerte >= tiempoMuerte){
                desaparecer();
            }
        }
    }

    public void daniar(int d) {
        if(salud>0){
            salud = Math.max(0, salud - d);
            if (salud == 0){
                morir();
            }
        }
    }

    public void desaparecer() {
        for (Bloque b : misBloques) {
            b.desocupar(this);
        }
        miMotor.eliminarEnemigo(this);
        miGrafica.desaparecer();
    }

    protected void morir() {
        for(Bloque b: misBloques){
            b.desocupar(this);
        }
        estado = new EnemigoMuriendo(this);
        miGrafica.animarMuerte(0);
    }

    public Rectangle2D getRango(){
        return rangoAtaque;
    }

    @Override
    public void visitar(FlechaElfo p) {
        daniar(p.getDamage());
        p.desaparecer();
    }

    @Override
    public void visitar(FlechaHumana p) {
        daniar(p.getDamage());
        p.desaparecer();
    }

    @Override
    public void visitar(Orbe p) {
        daniar(p.getDamage());
        setFraccionVelocidad(
                fraccionVelocidad - p.getRealentizacion(),
                p.getDuracionEfecto()
        );
        p.desaparecer();
    }

    @Override
    public void visitar(ArqueraHumana d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(ArqueroElfo d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(ElfoMele d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(CaballeroMele d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(Mago d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(Hada d) {
        d.daniar(damage);
    }

    @Override
    public void visitar(Escudero d) {
        d.daniar(damage);
    }

    public abstract void aceptar(VisitanteEnemigos v);

    protected abstract GraficaEnemigo crearGrafica();
    public boolean estaVivo(){
        return salud > 0;
    }
    protected abstract Sonido getSonidoImpacto();
    protected abstract Sonido getSonidoMuerte();
}

