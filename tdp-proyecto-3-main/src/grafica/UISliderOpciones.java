package grafica;

import util.LectorImagenes;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseEvent;

public class UISliderOpciones extends BasicSliderUI{
    protected final double fraccionAnchoBorde;
    protected final double fraccionAltoBorde;
    protected final double porcentajeAnchoThumb;
    protected int altoBorde;
    protected int anchoBorde;
    private final String imgThumb;
    private final String imgBorde;
    private final String imgProgeso;

    public UISliderOpciones(String imgThumb, String imgBorde, String imgProgeso, double fraccionAnchoBorde, double fraccionAltoBorde, double porcentajeAnchoThumb) {
        this.imgThumb = imgThumb;
        this.imgBorde = imgBorde;
        this.imgProgeso = imgProgeso;
        this.fraccionAnchoBorde = fraccionAnchoBorde;
        this.fraccionAltoBorde = fraccionAltoBorde;
        this.porcentajeAnchoThumb = porcentajeAnchoThumb;
    }

    @Override
    protected TrackListener createTrackListener(JSlider slider) {
        return new TrackListener(){
            private boolean arrastrando;

            @Override
            public void mouseReleased(MouseEvent e) {
                arrastrando = false;
                super.mouseReleased(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (trackRect.contains(e.getPoint())) {
                    arrastrando = true;
                    super.mousePressed(e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //Modificado de la superclase
                int trackLeft;
                int trackRight;
                int thumbLeft;
                if (slider.isEnabled() && arrastrando) {
                    currentMouseX = e.getX();
                    currentMouseY = e.getY();

                    slider.setValueIsAdjusting(true);

                    thumbLeft = e.getX() - offset;
                    trackLeft = trackRect.x;
                    trackRight = xPositionForValue(slider.getMaximum() -
                            slider.getExtent());

                    thumbLeft = Math.max(thumbLeft, trackLeft);
                    thumbLeft = Math.min(thumbLeft, trackRight);
                    setThumbLocation(thumbLeft, thumbRect.y);
                    slider.setValue(valueForXPosition(thumbLeft));
                }
                slider.repaint();
            }
        };
    }

    @Override
    protected void calculateTrackBuffer() {
        trackBuffer = 0;
    }

    @Override
    protected void calculateGeometry() {
        calculateFocusRect();
        calculateContentRect();
        calculateBorder();
        calculateThumbSize();
        calculateTrackBuffer();
        calculateTrackRect();
        calculateTickRect();
        calculateLabelRect();
        calculateThumbLocation();
    }

    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();
        thumbRect.x += thumbRect.width/2;
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension((int) (contentRect.width* porcentajeAnchoThumb),
                contentRect.height -altoBorde*2);
    }

    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();
        trackRect.x += anchoBorde;
        trackRect.width -= anchoBorde * 2;
    }

    private void calculateBorder() {
        anchoBorde = (int) (contentRect.width * fraccionAnchoBorde);
        altoBorde = (int) (contentRect.height * fraccionAltoBorde);
    }

    @Override
    public void paintThumb(Graphics g) {
        Image imagenThumb = LectorImagenes.getInstance().getImagen(imgThumb, thumbRect.getSize());
        g.drawImage(imagenThumb, thumbRect.x, thumbRect.y,thumbRect.width,thumbRect.height,null);
    }

    @Override
    protected int xPositionForValue(int value) {
        //Modificado de la superclase
        int min = slider.getMinimum();
        int max = slider.getMaximum();
        int trackLength = trackRect.width - thumbRect.width;
        double valueRange = (double)max - (double)min;
        double pixelsPerValue = (double)trackLength / valueRange;
        int trackLeft = trackRect.x;
        int trackRight = trackRect.x + (trackRect.width - 1) - thumbRect.width;

        int xPosition = (int) (trackLeft + Math.round( pixelsPerValue * ((double)value - min)));

        xPosition = Math.max( trackLeft, xPosition );
        xPosition = Math.min( trackRight, xPosition );

        return xPosition;
    }

    @Override
    public int valueForXPosition(int xPos) {
        //Modificado de la superclase
        int value;
        final int minValue = slider.getMinimum();
        final int maxValue = slider.getMaximum();
        final int trackLength = trackRect.width - thumbRect.width;
        final int trackLeft = trackRect.x;
        final int trackRight = trackRect.x + (trackRect.width - 1);
        double valueRange;
        int distanceFromTrackLeft;
        int valueFromTrackLeft;
        double valuePerPixel;

        if ( xPos <= trackLeft ) {
            value = minValue;
        }
        else if ( xPos >= trackRight ) {
            value = maxValue;
        }
        else {
            distanceFromTrackLeft = xPos - trackLeft;
            valueRange = (double)maxValue - (double)minValue;
            valuePerPixel = valueRange / (double)trackLength;
            valueFromTrackLeft = (int)Math.ceil( distanceFromTrackLeft * valuePerPixel );

            value = minValue + valueFromTrackLeft;
        }
        return value;
    }

    @Override
    public void paintTrack(Graphics g) {
        Image imagenProgreso = LectorImagenes.getInstance().getImagen(imgProgeso, trackRect.getSize());
        g.drawImage(imagenProgreso,
                trackRect.x,
                trackRect.y,
                thumbRect.x,
                trackRect.y + trackRect.height - 1,
                0,
                0,
                imagenProgreso.getWidth(null) * (thumbRect.x-trackRect.x) / trackRect.width,
                imagenProgreso.getHeight(null),
                null);
        g.setColor(new Color(0,0,0,0));
        g.fillRect(thumbRect.x,
                thumbRect.y,
                trackRect.width - (thumbRect.x - trackRect.x) + 1,
                trackRect.height);

    }

    @Override
    public void paint(Graphics g, JComponent c) {
        ((Graphics2D) g).setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        if(g.getClipBounds().intersects(contentRect))
            paintBorder(g);
        super.paint(g, c);
    }

    @Override
    public void paintFocus(Graphics g) {
    }
    private void paintBorder(Graphics g) {
        Image imagenBorde = LectorImagenes.getInstance().getImagen(imgBorde, contentRect.getSize());
        g.drawImage(imagenBorde, contentRect.x, contentRect.y,contentRect.width,contentRect.height,null);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return getPreferredHorizontalSize();
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getMinimumHorizontalSize();
    }
}
