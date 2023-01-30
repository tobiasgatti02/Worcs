package logica.cartas;

import grafica.GraficaCarta;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Carta {
    protected final int precio;
    protected final int cooldown;
    protected int cooldownRestante;
    protected GraficaCarta miGrafica;
    protected final AtomicBoolean monedasSuficientes;

    public Carta(int p, int cdown) {
        precio = p;
        cooldown = cdown;
        cooldownRestante = 0;
        monedasSuficientes = new AtomicBoolean();
    }
    public int getPrecio() {
        return precio;
    }

    // debe actualizar el cooldown de la carta
    protected abstract void crearDefensor(Motor m, Bloque b, Tablero t);

    public synchronized void usar(Motor m, Bloque b, Tablero t){
        crearDefensor(m,b,t);
        cooldownRestante = cooldown;
        miGrafica.desactivar();
    }

    public synchronized void actualizarCooldown(int tiempo) {
        if (cooldownRestante > 0) {
            cooldownRestante = Math.max(0, cooldownRestante - tiempo);
            double porcentaje = (1 - ((double) cooldownRestante / (double) cooldown)) * 100;
            miGrafica.actualizarCooldown((int) Math.round(porcentaje));
        }
        else{
            if(monedasSuficientes.get())
                miGrafica.activar();
        }
    }
    
    public void comprobarDisponibilidad(int monedas){
        boolean suficientes = monedas >= precio;
        monedasSuficientes.set(suficientes);
        if(!suficientes)
            SwingUtilities.invokeLater(() -> miGrafica.desactivar());
    }
    
    public synchronized boolean estaDisponible() {
        return cooldownRestante == 0;
    }

    public GraficaCarta getGrafica() {
        return miGrafica;
    }
}