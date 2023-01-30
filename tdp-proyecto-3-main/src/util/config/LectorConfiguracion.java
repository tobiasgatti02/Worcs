package util.config;

import java.io.IOException;
import java.util.Properties;

public class LectorConfiguracion {
    private static final String rutaConfigImagenes = "/config/imagenes.properties";
    private static final String rutaConfigTicks = "/config/ticks.properties";
    private static final String rutaConfigTablero = "/config/tablero.properties";
    private static final String rutaConfigCartas = "/config/cartas.properties";
    private static final String rutaConfigNiveles = "/config/niveles.properties";
    private static final String rutaConfigEntidades = "/config/entidades.properties";

    private static final String rutaConfigGraficas = "/config/graficas.properties";
    private static final String rutaConfigSonidos = "/config/sonidos.properties";
    private static final String rutaConfigTextosGUI = "/config/textosGUI.properties";

    protected final Properties imagenes;
    protected final Properties ticks;
    protected final Properties tablero;
    protected final Properties cartas;
    protected final Properties estrategias;
    protected final Properties entidades;
    protected final Properties graficas;
    protected final Properties sonidos;
    protected final Properties textosGUI;

    private static LectorConfiguracion instance;

    protected LectorConfiguracion() {
        imagenes = new Properties();
        ticks = new Properties();
        tablero = new Properties();
        cartas = new Properties();
        estrategias = new Properties();
        entidades = new Properties();
        graficas = new Properties();
        sonidos = new Properties();
        textosGUI = new Properties();
        try {
            imagenes.load(getClass().getResourceAsStream(rutaConfigImagenes));
            ticks.load(getClass().getResourceAsStream(rutaConfigTicks));
            tablero.load(getClass().getResourceAsStream(rutaConfigTablero));
            cartas.load(getClass().getResourceAsStream(rutaConfigCartas));
            estrategias.load(getClass().getResourceAsStream(rutaConfigNiveles));
            entidades.load(getClass().getResourceAsStream(rutaConfigEntidades));
            graficas.load(getClass().getResourceAsStream(rutaConfigGraficas));
            sonidos.load(getClass().getResourceAsStream(rutaConfigSonidos));
            textosGUI.load(getClass().getResourceAsStream(rutaConfigTextosGUI));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getConfig(ConfigIntImagenes c){
        return Integer.parseInt(imagenes.getProperty(c.getClave()));
    }
    public String getConfig(ConfigStringImagenes c){
        return imagenes.getProperty(c.getClave());
    }

    public double getConfig(ConfigDoubleImagenes c){
        return Double.parseDouble(imagenes.getProperty(c.getClave()));
    }
    public int getConfig(ConfigIntTicks c){
        return Integer.parseInt(ticks.getProperty(c.getClave()));
    }
    public int getConfig(ConfigIntTablero c){
        return Integer.parseInt(tablero.getProperty(c.getClave()));
    }
    public double getConfig(ConfigDoubleTablero c){
        return Double.parseDouble(tablero.getProperty(c.getClave()));
    }
    public int getConfig(ConfigIntCartas c){
        return Integer.parseInt(cartas.getProperty(c.getClave()));
    }

    public double getConfig(ConfigDoubleEntidades c){
        return Double.parseDouble(entidades.getProperty(c.getClave()));
    }
    public double getConfig(ConfigDoubleNiveles c){
        return Double.parseDouble(estrategias.getProperty(c.getClave()));
    }

    public double getConfig(ConfigDoubleGraficas c){
        return Double.parseDouble(graficas.getProperty(c.getClave()));
    }

    public int getConfig(ConfigIntEntidades c){
        return Integer.parseInt(entidades.getProperty(c.getClave()));
    }

    public int getConfig(ConfigIntNiveles c){
        return Integer.parseInt(estrategias.getProperty(c.getClave()));
    }
    public String getConfig(ConfigStringTextosGUI c){
        return textosGUI.getProperty(c.getClave());
    }

    public boolean getConfig(ConfigBooleanGraficas c){
        return graficas.getProperty(c.getClave()).equalsIgnoreCase("true");
    }
    public String getConfig(ConfigStringSonidos c){
        return sonidos.getProperty(c.getClave());
    }
    public int getConfig(ConfigIntSonidos c) {
        return Integer.parseInt(sonidos.getProperty(c.getClave()));
    }
    public static LectorConfiguracion getInstance() {
        if (instance == null)
            instance = new LectorConfiguracion();
        return instance;
    }
}