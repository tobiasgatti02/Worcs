package logica;

import grafica.GraficaMoneda;
import util.ReproductorSonidos;
import util.Sonido;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.util.*;

public class Moneda {
    protected final GraficaMoneda miGrafica;
    protected final Timer miTimer;
    protected final Juego juego;
    public Moneda(Point2D p, Juego j, Tablero t) {
        miGrafica = new GraficaMoneda(this, p,t.getGrafica());
        juego = j;
        miTimer = new Timer();
        miTimer.schedule(taskDesaparecer,LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_DESAPARECER_MONEDA));
    }

    public void recoger() {
        juego.aumentarMonedas(LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.VALOR_MONEDA));
        ReproductorSonidos.getInstance().reproducirSFX(Sonido.RECOGER_MONEDA);
        juego.eliminarMoneda(this);
        desaparecer();
    }

    protected final TimerTask taskDesaparecer = new TimerTask()
    {
        @Override
        public void run()
        {
            desaparecer();
        }
    };
    public synchronized void desaparecer() {
        taskDesaparecer.cancel();
        juego.eliminarMoneda(this);
        miGrafica.desaparecer();
    }
}