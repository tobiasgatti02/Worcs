package grafica;
import grafica.entidades.GraficaDefensor;
import grafica.entidades.GraficaEnemigo;
import grafica.entidades.GraficaEntidad;
import grafica.entidades.GraficaProyectil;

import grafica.oyentes.OyenteBloque;
import logica.Tablero;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
public class GraficaTablero{

    protected final Tablero miTablero;
    protected final Ventana miVentana;
    protected final JPanel panelBloques;
    protected final JLayeredPane panelEntidades;

    public GraficaTablero(Tablero t, Ventana v) {
        miTablero = t;
        miVentana = v;

        panelBloques = new JPanel();
        panelBloques.setOpaque(false);
        panelBloques.setLayout(new MigLayout(new LC()
                .fill()
                .insets("0")
                .gridGap("0","0")));

        panelEntidades = new JLayeredPane();
        panelEntidades.setOpaque(false);
        panelEntidades.setLayout(null);

        v.agregarTablero(panelBloques, panelEntidades);
    }

    public void agregarBloque(GraficaBloque b, int f, int c) {
        UtilidadesGraficas.ejecutarEnEDT(()->{
            panelBloques.add(b, new CC()
                    .cell(c,f)
                    .grow()
                    .push());
            b.addMouseListener(new OyenteBloque(b, miTablero.getGrafica().getVentana()));
        });
    }

    public void agregarEntidad(GraficaEnemigo g) {
        agregarComponenteLayout(g,3);
    }
    public void agregarEntidad(GraficaDefensor g) {
        agregarComponenteLayout(g,2);
    }
    public void agregarEntidad(GraficaProyectil g) {
        agregarComponenteLayout(g,1);
    }

    private void agregarComponenteLayout(Component g, int layer){
        UtilidadesGraficas.ejecutarEnEDT(()->{
            panelEntidades.add(g);
            panelEntidades.revalidate();
            panelEntidades.setLayer(g, layer);
        });
    }

    private void removerComponenteLayout(Component g) {
        UtilidadesGraficas.ejecutarEnEDT(()->{
            panelEntidades.remove(g);
            panelEntidades.revalidate();
            panelEntidades.repaint(g.getBounds());
        });
    }

    public void removerEntidad(GraficaEntidad g) {
        removerComponenteLayout(g);
    }

    public void agregarMoneda(GraficaMoneda g) {
        agregarComponenteLayout(g,4);
    }

    public void eliminarMoneda(GraficaMoneda g) {
        removerComponenteLayout(g);
    }
    public Ventana getVentana() {
        return miVentana;
    }

    public Tablero getTablero() {
        return miTablero;
    }

    public Rectangle mapearDesdeLogica(Rectangle2D r) {
        Dimension size = panelBloques.getSize();
        Point inicio = panelBloques.getLocation();
        inicio.translate(-panelEntidades.getX(), -panelEntidades.getY());
        Rectangle mapeado = new Rectangle();
        mapeado.setRect(miTablero.mapear(r, new Rectangle2D.Double(
                inicio.x,
                inicio.y,
                size.getWidth(),
                size.getHeight()
        )));
        return mapeado;
    }

    public Point mapearDesdeLogica(Point2D p) {
        Dimension size = panelBloques.getSize();
        Point mapeado = new Point();
        Point inicio = panelBloques.getLocation();
        inicio.translate(-panelEntidades.getX(), -panelEntidades.getY());
        mapeado.setLocation(miTablero.mapear(p, new Rectangle2D.Double(
                inicio.x,
                inicio.y,
                size.getWidth(),
                size.getHeight()
        )));
        return mapeado;
    }

    public void destruir(Collection<GraficaBloque> bloquesAEliminar) {
        UtilidadesGraficas.ejecutarEnEDT(() -> {
            for (GraficaBloque gb : bloquesAEliminar) {
                panelBloques.remove(gb);
            }
            panelBloques.revalidate();
            panelBloques.repaint();
            miVentana.eliminarTablero(panelBloques, panelEntidades);
        });
    }
}