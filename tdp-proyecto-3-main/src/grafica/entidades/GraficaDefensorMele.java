package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.defensores.DefensorMele;
import util.config.ConfigBooleanGraficas;
import util.config.LectorConfiguracion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GraficaDefensorMele extends GraficaDefensor {
    protected final double porcentajeExtraAncho;
    protected final double porcentajeExtraAlto;

    protected final DefensorMele miDefensor;
    public GraficaDefensorMele(DefensorMele d, GraficaTablero g, AnimacionPorcentaje ataque, AnimacionTemporizada normal, double porcExtraAncho, double porcExtraAlto) {
        super(g, ataque, normal);
        miDefensor = d;
        porcentajeExtraAncho = porcExtraAncho;
        porcentajeExtraAlto = porcExtraAlto;
        init();
    }

    @Override
    protected Rectangle getRectGrafica() {
        Rectangle2D hitbox = miDefensor.getHitbox();
        Rectangle2D rango = miDefensor.getRango();

        double altoExtra = hitbox.getHeight() * porcentajeExtraAlto / 100;
        double anchoExtra = rango.getWidth() * porcentajeExtraAncho / 100;
        //Alinear abajo a la izquierda
        return miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                hitbox.getX(),
                hitbox.getY() - altoExtra,
                hitbox.getWidth() + anchoExtra + rango.getWidth(),
                hitbox.getHeight() + altoExtra
        ));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (LectorConfiguracion.getInstance().getConfig(ConfigBooleanGraficas.MOSTRAR_HITBOX)) {
            g.translate(-getX(), -getY());
            Rectangle2D hitbox = miDefensor.getHitbox();
            Rectangle2D rango = miDefensor.getRango();

            Rectangle rangoGrafica = miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                    hitbox.getX() + hitbox.getWidth(),
                    hitbox.getY(),
                    rango.getWidth(),
                    rango.getHeight()
            ));

            Rectangle hitboxGrafica = miTablero.mapearDesdeLogica(miDefensor.getHitbox());
            g.setColor(Color.GREEN);
            g.drawRect(hitboxGrafica.x,
                    hitboxGrafica.y,
                    hitboxGrafica.width -1,
                    hitboxGrafica.height - 1);

            g.setColor(Color.RED);
            g.drawRect(rangoGrafica.x + 1,
                    rangoGrafica.y,
                    rangoGrafica.width - 3,
                    rangoGrafica.height - 2);
        }
    }
}
