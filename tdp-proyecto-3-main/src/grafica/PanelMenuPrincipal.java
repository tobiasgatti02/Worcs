package grafica;

import grafica.entidades.AnimacionTemporizada;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import util.ReproductorSonidos;
import util.config.*;
import util.opcionesUsuario.AdministradorOpciones;
import util.opcionesUsuario.OpcionesDouble;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.*;

public class PanelMenuPrincipal extends PanelImagen {
    private static final Font FUENTE_LABELS = new Font("Georgia",Font.PLAIN, UtilidadesGraficas.escalarFuenteSegunResolucion(30));
    private static final Font FUENTE_TITULOS = new Font("Georgia",Font.PLAIN, UtilidadesGraficas.escalarFuenteSegunResolucion(40));
    public final int tiempoEntreUpdates;
    private final Timer timerFondo;
    private JButton botonAjustes;
    private final JButton botonSalir;
    private JPanel panelBotones;
    private final JButton botonPuente;
    private final JButton botonCastillo;
    private JPanel panelAjustes;
    private JPanel panelCreditos;
    private JSlider sliderSFX;
    private JSlider sliderMusica;
    private final AnimacionTemporizada animacionFondo;
    private JButton botonCreditos;
    private JPanel panelLicencias;

    public PanelMenuPrincipal() {
        final int cantImagenes = LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.CANT_IMAGENES_ANIMACION_FONDO_MENU);
        final int duracionLoop = LectorConfiguracion.getInstance().getConfig(ConfigIntImagenes.DURACION_LOOP_ANIMACION_FONDO_MENU);
        animacionFondo = new AnimacionTemporizada(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.RUTA_ANIMACION_FONDO_MENU),
                cantImagenes,
                duracionLoop);
        tiempoEntreUpdates = duracionLoop / cantImagenes;
        this.imagenActual = animacionFondo.getImagen();
        timerFondo = new Timer(tiempoEntreUpdates, e->actualizarFondo(tiempoEntreUpdates));
        timerFondo.start();
        setLayout(new MigLayout(new LC().fill().insets("0")));
        botonPuente = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_PUENTE));
        botonCastillo = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_CASTILLO));
        botonSalir = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_SALIR));

        botonPuente.addActionListener(e->timerFondo.stop());
        botonCastillo.addActionListener(e->timerFondo.stop());
        botonSalir.addActionListener(e->timerFondo.stop());
        mostrarPanelPrincipal();
    }

    private void mostrarPanelPrincipal() {
        removerPaneles();
        panelBotones = new JPanel();
        add(panelBotones, new CC().growY().width("21%!").alignX("right").alignY("center"));
        panelBotones.setOpaque(false);

        panelBotones.setLayout(new MigLayout(new LC()
                .fill()
                .flowY()
                .insets("10sp","0","10sp","4.9sp")
                .gridGap("0","4sp")
                ));


        botonAjustes = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_AJUSTES));
        botonCreditos = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_CREDITOS));
        botonCreditos.addActionListener((e)->mostrarPanelCreditos());
        botonAjustes.addActionListener(e->mostrarPanelAjustes());

        panelBotones.add(botonPuente, new CC().growY(1.0f).cell(0,0,2,1).growX());
        panelBotones.add(botonCastillo, new CC().growY(1.0f).cell(0,1,2,1).growX());
        panelBotones.add(botonAjustes, new CC().growY(1.0f).cell(0,2,1,1).growX());
        panelBotones.add(botonCreditos, new CC().growY(1.0f).cell(1,2,1,1).growX().gapX("0.5sp",""));
        panelBotones.add(botonSalir, new CC().growY(1.0f).cell(0,3,2,1).growX().gapY("5sp", ""));

        revalidate();
        repaint();
    }

    private void mostrarPanelCreditos(){
        JButton botonVolver;
        JButton botonLicencias;
        JPanel panelTextoCreditos;
        JLabel titulo;
        removerPaneles();
        panelCreditos = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_OPCIONES));
        add(panelCreditos, new CC()
                .width("20%!")
                .gapX("0", "5sp")
                .alignY("center")
                .alignX("right"));
        panelCreditos.setOpaque(false);
        panelCreditos.setLayout(new MigLayout(new LC()
                .fill()
                .flowY()
                .insets("5sp","10%","5sp","10%")
        ));
        titulo = new JLabel("Créditos");
        titulo.setFont(FUENTE_TITULOS);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        botonVolver = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_VOLVER_CREDITOS));
        botonLicencias = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_LICENCIAS));

        botonVolver.addActionListener(e->mostrarPanelPrincipal());
        botonLicencias.addActionListener(e->mostrarPanelLicencias());
        panelTextoCreditos = crearPanelTextoArchivo(LectorConfiguracion.getInstance().getConfig(ConfigStringTextosGUI.ARCHIVO_CREDITOS));

        panelCreditos.add(titulo, new CC()
                .growX()
                .cell(0,0,2,0)
                .alignX("center"));
        panelCreditos.add(panelTextoCreditos, new CC()
                .growX()
                .height("30sp!")
                .cell(0,1,2,1)
                .gapY("2sp",""));
        panelCreditos.add(botonVolver, new CC()
                .growX(1.0f)
                .height("13sp!")
                .cell(0,2,1,1)
                .gapY("4sp","")
                .pushX(1.0f)
                .gapX("0", "0.5sp"));
        panelCreditos.add(botonLicencias, new CC()
                .growX(1.0f)
                .height("13sp!")
                .cell(1,2,1,1)
                .gapY("4sp!","")
                .pushX(1.0f));
        revalidate();
        repaint();
    }
    private void mostrarPanelLicencias(){
        JButton btnVolver;
        JPanel panelTextoCreditos;
        JLabel titulo;
        removerPaneles();
        panelLicencias = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_OPCIONES));
        add(panelLicencias, new CC()
                .width("20%!")
                .gapX("0", "5sp")
                .alignY("center")
                .alignX("right"));
        panelLicencias.setOpaque(false);
        panelLicencias.setLayout(new MigLayout(new LC()
                .fill()
                .flowY()
                .insets("5sp","10%","5sp","10%")
        ));
        titulo = new JLabel("Licencias");
        titulo.setFont(FUENTE_TITULOS);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        btnVolver = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_VOLVER_MENU));

        btnVolver.addActionListener(e->mostrarPanelCreditos());
        panelTextoCreditos = crearPanelTextoArchivo(LectorConfiguracion.getInstance().getConfig(ConfigStringTextosGUI.ARCHIVO_LICENCIAS));

        panelLicencias.add(titulo, new CC()
                .growX()
                .alignX("center"));
        panelLicencias.add(panelTextoCreditos, new CC()
                .growX()
                .height("30sp!")
                .gapY("2sp",""));
        panelLicencias.add(btnVolver, new CC()
                .growX()
                .height("13sp!")
                .gapY("4sp",""));

        revalidate();
        repaint();
    }
    private void removerPaneles(){
        if (panelAjustes != null) {
            remove(panelAjustes);
            panelAjustes = null;
        }
        if(panelCreditos != null){
            remove(panelCreditos);
            panelCreditos = null;
        }
        if(panelBotones != null){
            remove(panelBotones);
            panelBotones = null;
        }
        if(panelLicencias != null){
            remove(panelLicencias);
            panelLicencias = null;
        }
        revalidate();
        repaint();
    }
    private JPanel crearPanelTextoArchivo(String archivo){
        JPanel panelTexto = new JPanel(new GridLayout(1,1)){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0,0, getWidth(), getHeight());
            }
        };
        panelTexto.setOpaque(false);
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setOpaque(false);
        panelTexto.setBackground(new Color(0,0,0,100));
        textPane.setHighlighter(null);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);

        StyledDocument doc = textPane.getStyledDocument();
        rellenarConArchivo(doc, archivo);
        textPane.setCaretPosition(0);
        panelTexto.add(scrollPane);
        return panelTexto;
    }

    private AttributeSet getAtributosTexto(){
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize(attr, UtilidadesGraficas.escalarFuenteSegunResolucion(20));
        StyleConstants.setForeground(attr, Color.WHITE);
        return attr;
    }
    private void rellenarConArchivo(StyledDocument d, String ruta){
        InputStream stream = getClass().getResourceAsStream(ruta);
        AttributeSet atributosTexto = getAtributosTexto();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
            String linea = reader.readLine();
            while(linea != null) {
                d.insertString(d.getLength(), linea + "\n", atributosTexto);
                linea = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void mostrarPanelAjustes(){
        JButton btnVolver;
        remove(panelBotones);
        panelAjustes = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_OPCIONES));
        panelAjustes.setOpaque(false);
        panelAjustes.setLayout(new MigLayout(new LC()
                .fill()
                .flowY()
                .insets("5sp","10%","5sp","10%")
        ));
        add(panelAjustes, new CC()
                .width("20%!")
                .gapX("0", "5sp")
                .alignY("center")
                .alignX("right"));
        JLabel lblVolMusica = new JLabel("Volumen música");
        lblVolMusica.setFont(FUENTE_LABELS);
        panelAjustes.add(lblVolMusica, new CC().alignX("center"));

        sliderMusica = new JSlider();
        agregarSlider(sliderMusica);

        JLabel lblVolSFX= new JLabel("Volumen SFX");
        lblVolSFX.setFont(FUENTE_LABELS);
        panelAjustes.add(lblVolSFX, new CC().alignX("center"));

        sliderSFX = new JSlider();
        agregarSlider(sliderSFX);

        btnVolver = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_VOLVER_MENU));
        panelAjustes.add(btnVolver, new CC().growX().height("13sp!").gapY("4sp",""));

        btnVolver.addActionListener(e->volverDeAjustes());
        sliderMusica.addChangeListener((c)->actualizarVolMusica(sliderMusica.getValue()));

        sliderMusica.setValue((int) (AdministradorOpciones.getInstance().getOpcion(OpcionesDouble.VOLUMEN_MUSICA)*100));
        sliderSFX.setValue((int) (AdministradorOpciones.getInstance().getOpcion(OpcionesDouble.VOLUMEN_SFX)*100));


        revalidate();
        repaint();
    }

    private void actualizarFondo(int tiempo) {
        animacionFondo.avanzar(tiempo);
        this.imagenActual = animacionFondo.getImagen();
        repaint();
    }

    private void agregarSlider(JSlider slider) {
        slider.setUI(new UISliderOpciones(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_THUMB_SLIDER_VOLUMEN),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BORDE_SLIDER_VOLUMEN),
                LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_PROGRESO_SLIDER_VOLUMEN),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.FRACCION_ANCHO_BORDE_SLIDER_VOLUMEN),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.FRACCION_ALTO_BORDE_SLIDER_VOLUMEN),
                LectorConfiguracion.getInstance().getConfig(ConfigDoubleImagenes.FRACCION_ANCHO_THUMB_SLIDER_VOLUMEN)
        ));
        slider.setOpaque(false);
        panelAjustes.add(slider, new CC()
                .alignX("center")
                .height("5sp!")
                .gapY("0","1.5sp")
        );
    }

    private void volverDeAjustes() {
        AdministradorOpciones.getInstance().setOpcion(OpcionesDouble.VOLUMEN_MUSICA, sliderMusica.getValue() / 100.0f);
        AdministradorOpciones.getInstance().setOpcion(OpcionesDouble.VOLUMEN_SFX, sliderSFX.getValue() / 100.0f);
        mostrarPanelPrincipal();
    }

    private void actualizarVolMusica(int porcentaje) {
        ReproductorSonidos.getInstance().establecerVolumenMusica(porcentaje / 100.0f);
    }

    public void agregarOyenteBotonPuente(ActionListener a){
        botonPuente.addActionListener(a);
    }

    public void agregarOyenteBotonCastillo(ActionListener a){
        botonCastillo.addActionListener(a);
    }

    public void agregarOyenteSalir(ActionListener a) {
        botonSalir.addActionListener(a);
    }
}
