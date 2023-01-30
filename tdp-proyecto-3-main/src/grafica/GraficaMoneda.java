package grafica;

import grafica.oyentes.OyenteMoneda;
import logica.Moneda;
import util.*;
import util.config.ConfigDoubleTablero;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraficaMoneda extends JButton {
    protected final GraficaTablero graficaTablero;
    protected final Moneda moneda;
    public GraficaMoneda(Moneda m, Point2D p, GraficaTablero g) {
        super();
        double alto;
        double ancho;
        LectorConfiguracion lector = LectorConfiguracion.getInstance();

        moneda = m;
        ancho = lector.getConfig(ConfigDoubleTablero.ANCHO_MONEDA);
        alto = lector.getConfig(ConfigDoubleTablero.ALTO_MONEDA);
        graficaTablero = g;


        setContentAreaFilled(false);
        setBorder(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addActionListener(new OyenteMoneda(this));
        Rectangle bounds = g.mapearDesdeLogica(new Rectangle2D.Double(
                p.getX(),
                p.getY(),
                ancho,
                alto
        ));
        setBounds(bounds);
        UtilidadesGraficas.ejecutarEnEDT(()->{
            Image img = LectorImagenes.getInstance().getImagen(
                lector.getConfig(ConfigStringImagenes.IMAGEN_MONEDAS),
                getBounds().getSize()
            );
            setIcon(new ImageIcon(img));
        });

        g.agregarMoneda(this);

    }
    public void clicMoneda(){
        moneda.recoger();
    }
    public void desaparecer(){
        graficaTablero.eliminarMoneda(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return getBounds().getSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getBounds().getSize();
    }
}