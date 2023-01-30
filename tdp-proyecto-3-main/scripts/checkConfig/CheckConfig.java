import util.config.*;

import java.io.IOException;
import java.util.Properties;

public class CheckConfig {
    private static boolean corecto;
    private static boolean noVacias;
    public static void main(String[] args) throws IOException {
        Properties p;
        String aux;

        corecto = true;
        noVacias = true;

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/sonidos.properties"));
        for (ConfigStringSonidos conf : ConfigStringSonidos.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigIntSonidos conf : ConfigIntSonidos.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/entidades.properties"));
        for (ConfigDoubleEntidades conf : ConfigDoubleEntidades.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigIntEntidades conf : ConfigIntEntidades.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/niveles.properties"));
        for (ConfigDoubleNiveles conf : ConfigDoubleNiveles.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigIntNiveles conf : ConfigIntNiveles.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/graficas.properties"));
        for (ConfigDoubleGraficas conf : ConfigDoubleGraficas.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigBooleanGraficas conf : ConfigBooleanGraficas.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/imagenes.properties"));
        for (ConfigStringImagenes conf : ConfigStringImagenes.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigIntImagenes conf : ConfigIntImagenes.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigDoubleImagenes conf : ConfigDoubleImagenes.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/tablero.properties"));
        for (ConfigDoubleTablero conf : ConfigDoubleTablero.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        for (ConfigIntTablero conf : ConfigIntTablero.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/cartas.properties"));
        for (ConfigIntCartas conf : ConfigIntCartas.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }

        p = new Properties();
        p.load(LectorConfiguracion.class.getResourceAsStream("/config/ticks.properties"));
        for (ConfigIntTicks conf : ConfigIntTicks.values()) {
            aux = p.getProperty(conf.getClave());
            chequear(aux, conf.getClave());
        }
        if(corecto) {
            if(noVacias)
                System.out.println("Todo correcto");
            else
                System.out.println("Están todas las propiedades, pero hay algunas vacías");
        }
        else
            System.err.println("Faltan propiedades");
    }

    private static void chequear(String aux, String clave) {
        if(aux == null) {
            System.err.println("FALTA PROPIEDAD " + clave);
            corecto = false;
        }
        else if(aux.equals("")) {
            System.out.println("Propiedad vacia: " + clave);
            noVacias = false;
        }
    }
}
