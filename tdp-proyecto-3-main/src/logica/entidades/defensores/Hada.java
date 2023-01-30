package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import util.Sonido;
import logica.entidades.VisitanteDefensores;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigDoubleTablero;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Hada extends Defensor {
    private final int tiempoCreacionMoneda;
    protected final int tiempoEntreMonedas;
    protected int tiempoDesdeMoneda;
    protected int tiempoDesdeInicioMoneda;

    protected Hada(Motor m, Bloque b, Tablero t, int salud, Rectangle2D hitbox, int tiempoEntreMonedas, int tiempoCreacionMoneda) {
        super(m,salud,b,t,hitbox);
        this.tiempoEntreMonedas = tiempoEntreMonedas;
        this.tiempoDesdeMoneda = 0;
        this.tiempoDesdeInicioMoneda = 0;
        this.tiempoCreacionMoneda = tiempoCreacionMoneda;
        init();
    }

    public static Hada create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_HADA);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_HADA);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_HADA);
        int tiempoMoneda = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_MONEDA_HADA);
        int tiempoCreacionMoneda = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_CREACION_MONEDA_HADA);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2, t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);

        return new Hada(m, b, t, salud, hitbox,tiempoMoneda, tiempoCreacionMoneda);
    }

    @Override
    public void actuar(int tiempo) {
        tiempoDesdeMoneda += tiempo;
        if(tiempoDesdeMoneda >= tiempoEntreMonedas - tiempoCreacionMoneda){
            //Animación de crear una moneda
            miGrafica.animarHabilidad(Math.min(100, tiempoDesdeInicioMoneda * 100 / tiempoCreacionMoneda));
            if(tiempoDesdeInicioMoneda >= tiempoCreacionMoneda) {
                crearMoneda();
                reproducirSonido(getSonidoMoneda());
                tiempoDesdeInicioMoneda = 0;
                tiempoDesdeMoneda -= tiempoEntreMonedas;
            }
            tiempoDesdeInicioMoneda += tiempo;
        }
        else {
            miGrafica.animarNormal(tiempo);
        }
    }

    @Override
    public void aceptar(VisitanteDefensores v) {
        v.visitar(this);
    }

    @Override
    protected GraficaDefensor crearGrafica() {
        return GeneradorGraficaDefensores.crearGrafica(this,miTablero.getGrafica());
    }

    protected void crearMoneda() {
        double altoMoneda = LectorConfiguracion.getInstance().getConfig(ConfigDoubleTablero.ALTO_MONEDA);

        miTablero.getJuego().crearMoneda(new Point2D.Double(
                miHitbox.getX() + miHitbox.getWidth() * 3 /4,
                miHitbox.getCenterY() - altoMoneda / 2
        ));
    }
    protected Sonido getSonidoMoneda(){
        return Sonido.HADA_MONEDA;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return Sonido.MUERTE_HADA;
    }
}