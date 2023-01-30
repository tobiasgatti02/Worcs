package grafica;

import grafica.oyentes.OyenteCarta;
import logica.Juego;
import logica.ModoJuego;
import logica.niveles.GeneradorNiveles;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import util.LectorImagenes;
import util.ReproductorSonidos;
import util.Sonido;
import util.config.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class Ventana {
    protected final Collection<GraficaCarta> misCartas;
    private static final Font FUENTE_CONTADOR_MONEDAS = new Font("Arial", Font.BOLD, UtilidadesGraficas.escalarFuenteSegunResolucion(30));
    private static final Font FUENTE_NUMERO_NIVEL = new Font("Arial", Font.PLAIN, UtilidadesGraficas.escalarFuenteSegunResolucion(40));

    protected GraficaCarta cartaSeleccionada;
    protected boolean palaSeleccionada;
    protected Juego miJuego;

    private JFrame frame;
    private JPanel panelMonedas;
    private JPanel imagenMonedas;
    private JLabel contadorMonedas;

    private JPanel panelCartas;
    private JLayeredPane panelJuego;
    private GraficaPala pala;
    private PanelMenuPrincipal panelMenu;
    private JButton botonVolver;
    private PanelImagen superposicion;
    private JPanel panelMensajes;
    private PanelImagen panelFondo;
    private Timer timerSuperposicion;
    private IndicadorProgreso indicadorProgreso;
    private JPanel panelInfoNivel;
    private JLabel lblNumeroNivel;

    public Ventana() {
        misCartas = new ArrayList<>();
        cartaSeleccionada = null;
        palaSeleccionada = false;
        init();
    }
    public void setMonedas(int monedas){
        SwingUtilities.invokeLater(() -> contadorMonedas.setText(Integer.toString(monedas)));
    }

    private CC ccPanelCartas(){
        float porcentaje = misCartas.size() * 8.4f;
        return new CC()
                .cell(1,0,0,0)
                .height("22%!")
                .alignY("top")
                .alignX("left")
                .width(porcentaje+ "%!");
    }

    public void notificarHorda() {
        ReproductorSonidos.getInstance().reproducirSFX(Sonido.NOTIFICAR_HORDA);
        mostrarSuperposicion(Superposicion.NotificarHorda, null);
    }

    public void agregarCarta(GraficaCarta c) {
        UtilidadesGraficas.ejecutarEnEDT(()->{
            misCartas.add(c);

            // Agrandar panel
            ((MigLayout) panelJuego.getLayout()).setComponentConstraints(panelCartas, ccPanelCartas());
            // Añadir carta al panel
            panelCartas.add(c,new CC().growY().growX(1));

            panelJuego.revalidate();
            c.addActionListener(new OyenteCarta(c, this));
        });
    }

    public void vaciarCartas() {
        UtilidadesGraficas.ejecutarEnEDT(() -> {
           for (GraficaCarta carta : misCartas) {
               panelCartas.remove(carta);
           }
           misCartas.clear();

           // Achicar panel
           ((MigLayout) panelJuego.getLayout()).setComponentConstraints(panelCartas, ccPanelCartas());
           panelJuego.revalidate();
        });
    }

    public void alternarSeleccion(GraficaCarta g) {
        ReproductorSonidos.getInstance().reproducirSFX(Sonido.CARTA_SELECCIONADA);
        if(g == cartaSeleccionada) {
            g.marcarSeleccionada(false);
            cartaSeleccionada = null;
        }else {
            g.marcarSeleccionada(true);
            if(cartaSeleccionada != null)
                cartaSeleccionada.marcarSeleccionada(false);
            if(palaSeleccionada){
                pala.marcarSeleccionada(false);
                palaSeleccionada = false;
            }
            cartaSeleccionada = g;
        }
    }

    public void agregarTablero(JComponent panelBloques, JComponent panelEntidades) {
        UtilidadesGraficas.ejecutarEnEDT(()-> {
            panelJuego.add(panelBloques, new CC()
                    .cell(0, 1)
                    .grow()
                    .pushY()
                    .spanX());
            panelJuego.add(panelEntidades, new CC()
                    .pos("0", "0")
                    .width("100%!")
                    .height("100%!"));
            panelJuego.setLayer(panelEntidades, 2);
            panelJuego.setLayer(panelBloques, 1);
        });
    }


    public void eliminarTablero(JComponent panelBloques, JComponent panelEntidades) {
        UtilidadesGraficas.ejecutarEnEDT(()-> {
           panelJuego.remove(panelBloques);
            panelJuego.remove(panelEntidades);
            panelJuego.revalidate();
        });
    }


    /**
     * Deselecciona la carta, y alterna la selección de la pala
     * Cambia el cursor.
     */
    public void clicPala() {
        if(!palaSeleccionada){
            if(cartaSeleccionada != null)
            {
                cartaSeleccionada.marcarSeleccionada(false);
                cartaSeleccionada = null;
            }
            palaSeleccionada = true;
            pala.marcarSeleccionada(true);
        }else{
            palaSeleccionada = false;
            pala.marcarSeleccionada(false);
        }
    }

    public void clicEnBloque(GraficaBloque b){
        if(cartaSeleccionada != null) {
            cartaSeleccionada.marcarSeleccionada(false);
            miJuego.usarCarta(b.getBloque(), cartaSeleccionada.getCarta());
            cartaSeleccionada = null;
        }
        else if(palaSeleccionada){
            miJuego.usarPala(b.getBloque());
            ReproductorSonidos.getInstance().reproducirSFX(Sonido.PALA);
            palaSeleccionada = false;
            pala.marcarSeleccionada(false);
        }

        mouseOutBloque(b);
    }
    private void init() {
        frame = new JFrame();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(LectorConfiguracion.getInstance().getConfig(ConfigStringTextosGUI.NOMBRE_VENTANA));

        try {
            frame.setIconImage(ImageIO.read(getClass().getResource(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_ICONO_VENTANA))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int ancho = gd.getDisplayMode().getWidth();
        int alto = gd.getDisplayMode().getHeight();

        frame.setResizable(false);
        frame.setBounds(0, 0, ancho, alto);

        initMenu();
        frame.setVisible(true);

    }

    private void initMenu(){
        panelMenu = new PanelMenuPrincipal();
        frame.setContentPane(panelMenu);
        ReproductorSonidos.getInstance().reproducirMusica(Sonido.MUSICA_MENU);
        panelMenu.agregarOyenteBotonPuente(e-> iniciarPuente());
        panelMenu.agregarOyenteBotonCastillo(e -> iniciarCastillo());
        panelMenu.agregarOyenteSalir(e->salir());
    }

    private void salir() {
        System.exit(0);
    }

    //Debe invocarse desde EDT
    private void iniciarPuente() {
        initJuego(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_PUENTE));
        ReproductorSonidos.getInstance().pararMusica();
        ReproductorSonidos.getInstance().reproducirMusica(Sonido.MUSICA_PUENTE);
        miJuego = new Juego(this, 1, ModoJuego.ModoPuente);
    }

    //Debe invocarse desde EDT
    private void iniciarCastillo() {
        initJuego(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_CASTILLO));
        ReproductorSonidos.getInstance().pararMusica();
        ReproductorSonidos.getInstance().reproducirMusica(Sonido.MUSICA_CASTILLO);
        miJuego = new Juego(this, 1, ModoJuego.ModoCastillo);
    }

    protected void crearIndicadorProgreso(java.util.List<Float> hordas){
        UtilidadesGraficas.ejecutarEnEDT(()->{
            indicadorProgreso = new IndicadorProgreso(hordas);
            panelInfoNivel.add(indicadorProgreso,new CC()
                    .cell(1,0,0,0)
                    .alignX("center")
                    .alignY("top")
                    .width("20sp!")
                    .height("7sp!")
            );
        });
    }

    public CompletableFuture<Void> mostrarCuentaAtras(ModoJuego modo){
        botonVolver.setVisible(false);
        CompletableFuture<Void> futureCuenta = CompletableFuture.runAsync(() -> cuentaAtras());
        CompletableFuture<Void> futurePrecarga = CompletableFuture.runAsync(() -> LectorImagenes.getInstance().precargarImagenes(modo));
        return CompletableFuture.allOf(futureCuenta, futurePrecarga).thenRun(()->SwingUtilities.invokeLater(()->botonVolver.setVisible(true)));
    }

    private void cuentaAtras(){
        int delay = 1000 * 100 / LectorConfiguracion.getInstance().getConfig(ConfigIntTicks.PORCENTAJE_VELOCIDAD);
        try {
            mostrarSuperposicion(Superposicion.CuentaAtras3, null);
            Thread.sleep(delay);
            mostrarSuperposicion(Superposicion.CuentaAtras2, null);
            Thread.sleep(delay);
            mostrarSuperposicion(Superposicion.CuentaAtras1, null);
            Thread.sleep(delay);
            mostrarSuperposicion(Superposicion.CuentaAtras0, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void mostrarSuperposicion(Superposicion sup, Runnable ejecutarDespues) {
        String rutaImagen = LectorConfiguracion.getInstance().getConfig(sup.getImagen());
        double porcentajeAncho = LectorConfiguracion.getInstance().getConfig(sup.getPorcentajeAncho());
        double porcentajeAlto = LectorConfiguracion.getInstance().getConfig(sup.getPorcentajeAlto());
        int duracion = LectorConfiguracion.getInstance().getConfig(sup.getDuracion());

        UtilidadesGraficas.ejecutarEnEDT(() -> {
            if (timerSuperposicion != null) {
               timerSuperposicion.stop();
            }
            eliminarSuperposicion();

            superposicion = new PanelImagen(rutaImagen);
            superposicion.setOpaque(false);
            timerSuperposicion = new Timer(duracion, (e)-> {
                eliminarSuperposicion();
                if(ejecutarDespues != null)
                    ejecutarDespues.run();
            });
            timerSuperposicion.start();
            timerSuperposicion.setRepeats(false);
            panelMensajes.add(superposicion, new CC().width(porcentajeAncho+"%!").height(porcentajeAlto+"%!").alignY("center").alignX("center"));
            panelMensajes.repaint();
            panelMensajes.setVisible(true);
        });
    }

    private void eliminarSuperposicion() {
        UtilidadesGraficas.ejecutarEnEDT(()->{
        if(superposicion != null) {
            panelMensajes.remove(superposicion);
            panelMensajes.revalidate();
            panelMensajes.repaint();
            panelMensajes.setVisible(false);
        }
        });
    }

    //Debe invocarse desde EDT
    private void initJuego(String imgFondo){
        panelJuego = new JLayeredPane();
        panelMensajes = new JPanel();
        panelMensajes.setVisible(false);
        panelMensajes.setLayout(new MigLayout(new LC().fill()));
        panelMensajes.setOpaque(false);
        frame.setContentPane(panelJuego);
        panelJuego.setLayout(new MigLayout(new LC()
                .fill()
                .alignY("top")
                .alignX("left")
                .insets("0")
                .gridGap("0","0")
        ));
        panelJuego.add(panelMensajes, new CC().pos("0", "0").width("100%!").height("100%!"));
        panelFondo = new PanelImagen(imgFondo);
        panelJuego.add(panelFondo, new CC().pos("0", "0").width("100%!").height("100%!"));

        panelMonedas = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_PANEL_MONEDAS));
        panelMonedas.setLayout(new MigLayout(new LC().fill().insets("0").gridGap("0", "0")));

        panelMonedas.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));

        imagenMonedas = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_MONEDAS));
        imagenMonedas.setOpaque(false);

        panelJuego.add(panelMonedas, new CC()
                .cell(0,0,0,0)
                .height("22%!")
                .width("10%!")
                .alignY("top")
        );
        panelMonedas.add(imagenMonedas, new CC()
                .push()
                .grow()
                .wrap()
                .height("50%!")
                .width("60%!")
                .alignX("center")
                .alignY("center")
                .gapY("15%!", "2%!")
        );

        contadorMonedas = new JLabel("0");
        contadorMonedas.setForeground(Color.WHITE);
        contadorMonedas.setFont(FUENTE_CONTADOR_MONEDAS);
        panelMonedas.add(contadorMonedas, new CC()
                .alignX("center")
                .minHeight("10%")
                .gapY("2%!", "15%!")
        );


        panelCartas = new PanelImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_FONDO_PANEL_MONEDAS));
        panelCartas.setLayout(new MigLayout(new LC().fill()));
        panelJuego.add(panelCartas, ccPanelCartas());

        pala = new GraficaPala(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_PALA), LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_PALA_SELECCIONADA));
        panelJuego.add(pala, new CC()
                .cell(2,0,0,0)
                .width("4%!")
                .height("18%!")
                .alignX("left")
                .alignY("top")
                .gapLeft("0.2%!")
                .gapTop("2%!")
        );
        pala.addActionListener(e -> clicPala());
        panelInfoNivel = new JPanel(new MigLayout(new LC().fill()));
        panelInfoNivel.setOpaque(false);
        lblNumeroNivel = new JLabel();
        lblNumeroNivel.setBackground(new Color(0,0,0,180));
        lblNumeroNivel.setOpaque(true);
        lblNumeroNivel.setForeground(Color.WHITE);
        panelInfoNivel.add(lblNumeroNivel,new CC()
                .cell(0,0,0,0)
                .alignY("center")
                );
        lblNumeroNivel.setFont(FUENTE_NUMERO_NIVEL);
        panelJuego.add(panelInfoNivel, new CC()
                .cell(3,0,0,0)
                .alignX("center")
                .pushX()
                .alignY("top")
                .gapTop("1sp!")
                .gapRight("1sp!")
        );
        botonVolver = new BotonImagen(LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.IMAGEN_BOTON_VOLVER_JUEGO));

        panelJuego.add(botonVolver, new CC()
                .cell(4,0,0,0)
                .alignX("right")
                .alignY("top")
                .width("8.5%!")
                .height("12%!")
                .gapTop("1%!")
                .gapRight("1%!")
        );
        botonVolver.addActionListener(e -> volverMenu());
        panelJuego.setLayer(panelFondo, 0);
        panelJuego.setLayer(panelMensajes, 3);
        panelJuego.setLayer(panelMonedas, 4);
        panelJuego.setLayer(panelCartas, 4);
        panelJuego.setLayer(botonVolver, 4);
        panelJuego.setLayer(panelInfoNivel, 4);

    }

    //Debe invocarse desde EDT
    private void volverMenu() {
        miJuego.parar();
        miJuego = null;
        ReproductorSonidos.getInstance().pararMusica();
        initMenu();
    }

    //Debe invocarse desde EDT
    public void mouseInBloque(GraficaBloque gb) {
        if (palaSeleccionada || (cartaSeleccionada != null && gb.getBloque().getDefensor() == null)) {
            gb.mostrarEfectoHover();
            frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    //Debe invocarse desde EDT
    public void mouseOutBloque(GraficaBloque gb) {
       gb.ocultarEfectoHover();
       frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void gameOver() {
        ReproductorSonidos.getInstance().reproducirSFX(Sonido.GAME_OVER);
        mostrarSuperposicionFinal(Superposicion.GameOver);
    }

    private void mostrarSuperposicionFinal(Superposicion superposicion){
        ReproductorSonidos.getInstance().pararMusica();
        UtilidadesGraficas.ejecutarEnEDT(()->{
            mostrarSuperposicion(superposicion, this::volverMenu);
            panelJuego.remove(panelMonedas);
            panelJuego.remove(botonVolver);
            panelJuego.remove(pala);
            panelJuego.revalidate();
            panelJuego.repaint();
        });
    }

    public void nivelTerminado(ModoJuego modo, int numero) {
        UtilidadesGraficas.ejecutarEnEDT(()->{
            panelInfoNivel.remove(indicadorProgreso);
            lblNumeroNivel.setText("");
            panelInfoNivel.revalidate();
            panelInfoNivel.repaint();
        });
        if(numero < GeneradorNiveles.getInstance().cantNiveles(modo)){
            mostrarSuperposicion(Superposicion.NivelCompletado, ()-> {
                miJuego = new Juego(this, numero + 1, modo);
            });
        }else{
            mostrarSuperposicionFinal(Superposicion.Victoria);
        }
    }

    public void actualizarNivel(int i, java.util.List<Float> hordas) {
        UtilidadesGraficas.ejecutarEnEDT(()-> {
            lblNumeroNivel.setText("Nivel " + i);
            crearIndicadorProgreso(hordas);
        });
    }

    public void actualizarProgreso(float porcentajeAvance) {
        if(indicadorProgreso != null)
            indicadorProgreso.actualizarPorcentaje(porcentajeAvance);
    }
}