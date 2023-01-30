package grafica.entidades;

import grafica.GraficaTablero;
import grafica.UtilidadesGraficas;
import logica.entidades.proyectiles.Proyectil;
import util.config.ConfigBooleanGraficas;
import util.config.LectorConfiguracion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GraficaProyectil extends GraficaEntidad {
    protected final AnimacionTemporizada animacionMovimiento;
    protected final Proyectil miProyectil;
    public GraficaProyectil(Proyectil p, GraficaTablero g, AnimacionTemporizada animacion) {
        super(g, animacion);
        animacionMovimiento = animacion;
        miProyectil = p;
        miTablero.agregarEntidad(this);
        init();
    }

    public void animarAvance(int tiempo) {
        UtilidadesGraficas.ejecutarEnEDT(()->setBounds(miTablero.mapearDesdeLogica(miProyectil.getHitbox())));
        animacionMovimiento.avanzar(tiempo);
        actualizarAnimacion();
    }

    @Override
    protected Rectangle getRectGrafica() {
        return miTablero.mapearDesdeLogica(miProyectil.getHitbox());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (LectorConfiguracion.getInstance().getConfig(ConfigBooleanGraficas.MOSTRAR_HITBOX)) {
            g.translate(-getX(), -getY());
            g.setColor(Color.GREEN);
            Rectangle2D hitbox = miProyectil.getHitbox();
            Rectangle hitboxGrafica = miTablero.mapearDesdeLogica(hitbox);
            g.drawRect(hitboxGrafica.x,
                    hitboxGrafica.y,
                    hitboxGrafica.width - 1,
                    hitboxGrafica.height - 1
            );
        }
    }
}