package grafica;

import util.LectorImagenes;
import util.config.ConfigDoubleImagenes;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class IndicadorProgreso extends JPanel {
    protected final List<Float> hordas;
    protected final String rutaBorde;
    protected final String rutaProgreso;
    protected final String rutaBanderaCompletada;
    protected final String rutaBanderaNormal;
    protected final double porcentajeAnchoBarra;
    protected final double porcentajeAltoBarra;
    protected final double porcentajeXBarra;
    protected final double porcentajeYBarra;
    protected final double porcentajeXBorde;
    protected final double porcentajeYBorde;
    protected final double porcentajeAnchoBandera;
    protected final double porcentajeYBandera;


    protected float porcentaje;
    protected volatile Image grafico;

    public IndicadorProgreso(List<Float> hordas) {
        this.hordas = hordas;
        this.rutaBorde = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BORDE_INDICADOR_PROGRESO);
        this.rutaProgreso = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_PROGRESO_INDICADOR_PROGRESO);
        this.rutaBanderaCompletada = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BANDERA_COMPLETADA_INDICADOR_PROGRESO);
        this.rutaBanderaNormal = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BANDERA_NORMAL_INDICADOR_PROGRESO);
        this.porcentajeAnchoBarra = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_ANCHO_BARRA_INDICADOR_PROGRESO);
        this.porcentajeAltoBarra = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_ALTO_BARRA_INDICADOR_PROGRESO);
        this.porcentajeXBarra = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_X_BARRA_INDICADOR_PROGRESO);
        this.porcentajeYBarra = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_Y_BARRA_INDICADOR_PROGRESO);
        this.porcentajeXBorde = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_X_BORDE_INDICADOR_PROGRESO);
        this.porcentajeYBorde = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_Y_BORDE_INDICADOR_PROGRESO);
        this.porcentajeAnchoBandera = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_ANCHO_BANDERA_INDICADOR_PROGRESO);
        this.porcentajeYBandera = LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.PORCENTAJE_Y_ABAJO_BANDERA);

        this.porcentaje = 0;
        setOpaque(false);
        actualizarGrafico();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarGrafico();
            }
        });
    }

    //No es necesario ejecutar en EDT
    public void actualizarPorcentaje(float porcentaje){
        if(porcentaje != this.porcentaje) {
            this.porcentaje = porcentaje;
            actualizarGrafico();
        }
    }

    private void actualizarGrafico() {
        if(getHeight() > 0 && getWidth() > 0) {
            BufferedImage graficoNuevo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = graficoNuevo.createGraphics();
//            g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            graficarBorde(g);
            graficarBarra(g);
            g.dispose();
            grafico = graficoNuevo;
            repaint();
        }
    }

    protected void graficarBorde(Graphics2D g) {
        int xBorde = (int) (porcentajeXBorde * getWidth() / 100);
        int yBorde = (int) (porcentajeYBorde * getHeight() / 100);

        Image imgBorde = LectorImagenes.getInstance().getImagen(rutaBorde, new Dimension(getWidth() - xBorde, getHeight() - yBorde));
        g.drawImage(imgBorde, xBorde, yBorde, null);
    }

    protected void graficarBarra(Graphics2D g){
        int xBarra = (int) (porcentajeXBarra * getWidth() / 100);
        int yBarra = (int) (porcentajeYBarra * getHeight() / 100);
        int anchoBarra = (int) (porcentajeAnchoBarra * getWidth() / 100);
        int anchoBandera = (int) (porcentajeAnchoBandera * getWidth() / 100);
        int altoBarra = (int) (porcentajeAltoBarra * getHeight() / 100);
        int anchoProgreso = (int) (porcentaje * anchoBarra / 100);
        int coordXBarraDerecha = xBarra + anchoBarra;
        int xBandera;
        int yBandera = (int) (porcentajeYBandera * getHeight() / 100);


        Image imgProgreso = LectorImagenes.getInstance().getImagen(
                rutaProgreso,
                new Dimension(anchoBarra, altoBarra));
        g.drawImage(imgProgreso,
                coordXBarraDerecha - anchoProgreso,
                yBarra,
                coordXBarraDerecha,
                yBarra + altoBarra,
                imgProgreso.getWidth(null) - anchoProgreso,
                0,
                imgProgreso.getWidth(null),
                imgProgreso.getHeight(null),
                null);

        for (Float porcentajeHorda : hordas) {
            xBandera = (int) (coordXBarraDerecha - porcentajeHorda * anchoBarra / 100);;
            graficarBandera(g, xBandera, yBandera, anchoBandera, porcentaje >= porcentajeHorda);
        }
    }
    protected void graficarBandera(Graphics2D g, int x, int y, int anchoBandera, boolean completada){
        String ruta = completada ? rutaBanderaCompletada : rutaBanderaNormal;
        Image img = LectorImagenes.getInstance().getImagen(ruta, new Dimension(anchoBandera, getHeight() -y));
        g.drawImage(img, x - anchoBandera / 2, 0, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(grafico,0,0,null);
    }
}
