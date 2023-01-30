package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.defensores.Defensor;
import util.config.ConfigBooleanGraficas;
import util.config.LectorConfiguracion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GraficaDefensorGenerico extends GraficaDefensor {
    protected final double porcentajeExtraAlto;

    protected final Defensor miDefensor;
    public GraficaDefensorGenerico(Defensor d, GraficaTablero g, AnimacionPorcentaje ataque, AnimacionTemporizada normal, double porcExtraAlto) {
        super(g, ataque, normal);
        miDefensor = d;
        porcentajeExtraAlto = porcExtraAlto;

        init();
    }

    @Override
    protected Rectangle getRectGrafica() {
        Rectangle2D hitbox = miDefensor.getHitbox();
        double altoExtra = hitbox.getHeight() * porcentajeExtraAlto / 100;
        //Alinear abajo a la izquierda
        return miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                hitbox.getX(),
                hitbox.getY() - altoExtra,
                hitbox.getWidth(),
                hitbox.getHeight() + altoExtra
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (LectorConfiguracion.getInstance().getConfig(ConfigBooleanGraficas.MOSTRAR_HITBOX)) {
            g.translate(-getX(), -getY());
            g.setColor(Color.GREEN);
            Rectangle2D hitbox = miDefensor.getHitbox();
            Rectangle hitboxGrafica = miTablero.mapearDesdeLogica(hitbox);
            g.drawRect(hitboxGrafica.x,
                    hitboxGrafica.y,
                    hitboxGrafica.width - 1,
                    hitboxGrafica.height - 1
                    );
        }
    }
}
