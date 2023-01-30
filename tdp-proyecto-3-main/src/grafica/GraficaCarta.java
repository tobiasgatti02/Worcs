package grafica;

import logica.cartas.Carta;
import util.LectorImagenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class GraficaCarta extends JButton {

    protected final Carta miCarta;
    protected final String imagenNormal;
    protected final String superposicionSeleccionada;
    protected final String superposicionDesactivada;
    protected final String superposicionCooldown;
    protected volatile Image imagen;
    protected int porcentajeCooldown;

    protected volatile boolean selecionada;

    public GraficaCarta(Carta c, String imgNormal, String supSeleccionada, String supDesactivada, String supCooldown) {
        this.miCarta = c;
        this.imagenNormal = imgNormal;
        this.superposicionSeleccionada = supSeleccionada;
        this.superposicionDesactivada = supDesactivada;
        this.superposicionCooldown = supCooldown;

        setOpaque(false);
        desactivar();
        actualizarCooldown(100);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarIcono();
            }
        });
    }

    public Carta getCarta() {
        return miCarta;
    }
    public void actualizarCooldown(int porcentaje) {
        porcentajeCooldown = porcentaje;
        actualizarIcono();
    }

    public void marcarSeleccionada(boolean sel) {
        UtilidadesGraficas.ejecutarEnEDT(()->{
            selecionada = sel;
            actualizarIcono();
        });
    }

    private void actualizarIcono(){
        if(getWidth() != 0 && getHeight() != 0) {
            final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            final GraphicsConfiguration config = device.getDefaultConfiguration();

            final BufferedImage img = config.createCompatibleImage(getWidth(), getHeight(), Transparency.TRANSLUCENT);
            final Graphics2D g = img.createGraphics();

            g.drawImage(LectorImagenes.getInstance().getImagen(imagenNormal, getSize()),
                    0,
                    0,
                    null);
            if (selecionada)
                g.drawImage(LectorImagenes.getInstance().getImagen(superposicionSeleccionada, getSize()),
                        0,
                        0,
                        null);
            if (!isEnabled())
                g.drawImage(LectorImagenes.getInstance().getImagen(superposicionDesactivada, getSize()),
                        0,
                        0,
                        null);
            if (porcentajeCooldown != 100)
                g.drawImage(LectorImagenes.getInstance().getImagen(superposicionCooldown, getSize()),
                        0,
                        0,
                        getWidth(),
                        getHeight() * (100 - porcentajeCooldown) / 100,
                        null);
            g.dispose();
            imagen = img;
            repaint();
        }
    }

    public void activar(){
        UtilidadesGraficas.ejecutarEnEDT(()->{
            if(!isEnabled()) {
                setEnabled(true);
                actualizarIcono();
            }
        });
    }
    public void desactivar(){
        UtilidadesGraficas.ejecutarEnEDT(()->{
            if(isEnabled()) {
                setEnabled(false);
                actualizarIcono();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}