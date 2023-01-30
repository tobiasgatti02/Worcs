package grafica.entidades;

import grafica.GraficaTablero;
import grafica.UtilidadesGraficas;
import logica.entidades.enemigos.Enemigo;
import util.config.ConfigBooleanGraficas;
import util.config.LectorConfiguracion;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GraficaEnemigo extends GraficaEntidad {
    private final double porcentajeExtraAltoMuerto;
    private final double porcentajeExtraAnchoMuerto;
    protected final Enemigo miEnemigo;
    protected final double porcentajeExtraAncho;
    protected final double porcentajeExtraAlto;

    protected final double distanciaAnimacionAvance;
    protected double avanceDesdeAnimacion;

    protected final AnimacionPorcentaje animacionAvance;
    protected final AnimacionPorcentaje animacionMuerte;
    protected final AnimacionPorcentaje animacionAtaque;

    public GraficaEnemigo(Enemigo e, GraficaTablero g, AnimacionPorcentaje ataque, AnimacionPorcentaje muerte,
                          AnimacionPorcentaje avance, double porcExtraAncho, double porcExtraAlto,
                          double distanciaAnimacionAvance, double porcExtraAnchoMuerto, double porcExtraAltoMuerto) {
        super(g, avance);
        miEnemigo = e;
        animacionAtaque = ataque;
        animacionMuerte = muerte;
        animacionAvance = avance;
        this.distanciaAnimacionAvance = distanciaAnimacionAvance;
        porcentajeExtraAncho = porcExtraAncho;
        porcentajeExtraAlto = porcExtraAlto;

        porcentajeExtraAltoMuerto = porcExtraAltoMuerto;
        porcentajeExtraAnchoMuerto = porcExtraAnchoMuerto;

        init();
        g.agregarEntidad(this);
    }

    public void animarAtaque(int porcentaje) {
        animacionActual = animacionAtaque;
        animacionAtaque.setPorcentaje(porcentaje);
        actualizarAnimacion();
    }

    public void animarMuerte(int porcentaje) {
        animacionActual = animacionMuerte;
        animacionMuerte.setPorcentaje(porcentaje);
        actualizarAnimacion();
        UtilidadesGraficas.ejecutarEnEDT(()->{
            setBounds(getRectGrafica());
        });
    }

     public void animarAvance(double avance) {
        UtilidadesGraficas.ejecutarEnEDT(()->setBounds(getRectGrafica()));
        if(animacionActual != animacionAvance)
            avanceDesdeAnimacion = 0;
        else {
             avanceDesdeAnimacion += avance;
             if (avanceDesdeAnimacion > distanciaAnimacionAvance) {
                 avanceDesdeAnimacion -= distanciaAnimacionAvance;
             }
         }
        animacionActual = animacionAvance;

        final int porcentaje = (int) Math.round(avanceDesdeAnimacion / distanciaAnimacionAvance * 100);
        animacionAvance.setPorcentaje(porcentaje);
        actualizarAnimacion();
     }

    @Override
    protected Rectangle getRectGrafica(){
        Rectangle2D hitbox = miEnemigo.getHitbox();
        Rectangle bounds;
        double anchoExtra;
        double altoExtra;
        if (miEnemigo.estaVivo()) {
            Rectangle2D rango = miEnemigo.getRango();
            anchoExtra = rango.getWidth() * porcentajeExtraAncho / 100;
            altoExtra = hitbox.getHeight() * porcentajeExtraAlto / 100;
            //Alinear abajo a la derecha
            bounds = miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                    hitbox.getX() - anchoExtra - rango.getWidth(),
                    hitbox.getY() - altoExtra,
                    hitbox.getWidth() + anchoExtra + rango.getWidth(),
                    hitbox.getHeight() + altoExtra
            ));
        } else {
            anchoExtra = hitbox.getWidth() * porcentajeExtraAnchoMuerto  / 100;
            altoExtra = hitbox.getHeight() * porcentajeExtraAltoMuerto / 100;
            bounds = miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                    hitbox.getX() - anchoExtra,
                    hitbox.getY() - altoExtra,
                    hitbox.getWidth() + anchoExtra,
                    hitbox.getHeight() + altoExtra
            ));
        }
        return bounds;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(LectorConfiguracion.getInstance().getConfig(ConfigBooleanGraficas.MOSTRAR_HITBOX)) {
            Graphics2D gr = (Graphics2D) g;

            gr.translate(-getX(), -getY());
            Rectangle2D hitbox = miEnemigo.getHitbox();
            if(miEnemigo.estaVivo()) {
                Rectangle2D rango = miEnemigo.getRango();

                Rectangle rangoGrafica = miTablero.mapearDesdeLogica(new Rectangle2D.Double(
                        hitbox.getX() - rango.getWidth(),
                        hitbox.getY(),
                        rango.getWidth(),
                        rango.getHeight()
                ));

                gr.setColor(Color.RED);
                gr.drawRect(rangoGrafica.x + 1,
                        rangoGrafica.y,
                        rangoGrafica.width - 2,
                        rangoGrafica.height - 2);
            }

            Rectangle hitboxGrafica = miTablero.mapearDesdeLogica(miEnemigo.getHitbox());
            gr.setColor(Color.GREEN);
            gr.drawRect(hitboxGrafica.x,
                    hitboxGrafica.y,
                    hitboxGrafica.width - 2,
                    hitboxGrafica.height - 2);
        }
    }
}