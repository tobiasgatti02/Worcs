package grafica;

import util.LectorImagenes;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonImagen extends JButton {
    private final String imagen;

    public BotonImagen(String img) {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setIcon(new ImageIcon(LectorImagenes.getInstance().getImagen(imagen, getSize())));
            }
        });
        imagen = img;
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorderPainted(false);
            }
        });
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorderPainted(false);
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
