package grafica;

import util.LectorImagenes;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

public class PanelImagen extends JPanel {
    protected String imagenActual;
    public PanelImagen(String imagen) {
        super();
        imagenActual = imagen;
    }
    protected PanelImagen(){
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(imagenActual != null) {
            super.paintComponent(g);
            g.drawImage(LectorImagenes.getInstance().getImagen(imagenActual, new Dimension(getSize())),0,0,null);
        }
    }
}