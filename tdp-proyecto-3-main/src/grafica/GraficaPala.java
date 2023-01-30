package grafica;
import util.LectorImagenes;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GraficaPala extends JButton {

    protected final String imagenNormal;
    protected final String imagenSeleccionada;

    protected boolean seleccionado;

    public GraficaPala(String imgNormal, String imgSeleccionado) {
        imagenNormal = imgNormal;
        imagenSeleccionada = imgSeleccionado;
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarIcono();
            }
        });
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
    }
    public void marcarSeleccionada(boolean sel) {
        seleccionado = sel;
        actualizarIcono();
    }

    private void actualizarIcono(){
        String ruta = seleccionado ? imagenSeleccionada: imagenNormal;
        Image img = LectorImagenes.getInstance().getImagen(ruta,getSize());
        setIcon(new ImageIcon(img));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1,1);
    }
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(1,1);
    }
}