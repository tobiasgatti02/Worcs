package grafica.entidades;

import grafica.GraficaTablero;
import logica.entidades.defensores.Defensor;
import logica.entidades.defensores.Escudero;
import util.config.ConfigBooleanGraficas;
import util.config.LectorConfiguracion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GraficaEscudero extends GraficaDefensor {
    protected final double porcentajeExtraAlto;
    protected final double porcentajeExtraAncho;
    protected final Defensor miDefensor;

    public GraficaEscudero(Escudero d, GraficaTablero g, AnimacionPorcentaje habilidad, AnimacionTemporizada normal, double porcExtraAncho, double porcExtraAlto) {
        super(g, habilidad, normal);
        miDefensor = d;
        porcentajeExtraAncho = porcExtraAncho;
        porcentajeExtraAlto = porcExtraAlto;

        init();
    }

    @Override
    protected Rectangle getRectGrafica() {
        Rectangle2D hitbox = miDefensor.getHitbox();
        double anchoExtra = hitbox.getWidth() * porcentajeExtraAncho / 100;
        double altoExtra = hitbox.getHeight() * porcentajeExtraAlto / 100;
        //Alinear abajo a la izquierda
        return miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                hitbox.getX(),
                hitbox.getY() - altoExtra,
                hitbox.getWidth() + anchoExtra,
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
