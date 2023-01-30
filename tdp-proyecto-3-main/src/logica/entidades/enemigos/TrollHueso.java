package logica.entidades.enemigos;

import grafica.entidades.GeneradorGraficaEnemigos;
import grafica.entidades.GraficaEnemigo;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteEnemigos;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TrollHueso extends Enemigo {
    protected TrollHueso(Motor m, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int damage, double avancePorSegundo, int tiempoAtaque, int tiempoMuerte) {
        super(m, t, hitbox, salud, rango, damage, avancePorSegundo, tiempoAtaque, tiempoMuerte);
    }

    public static TrollHueso create(Motor m, Point2D posInicial, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_TROLL_HUESO);

        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_TROLL_HUESO);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_TROLL_HUESO);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_TROLL_HUESO);
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_TROLL_HUESO);
        int damage = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DAMAGE_TROLL_HUESO);
        double avancePorSegundo = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.AVANCE_POR_SEGUNDO_TROLL_HUESO);
        int tiempoMuerte = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_MUERTE_TROLL_HUESO);

        Rectangle2D hitbox = new Rectangle2D.Double(posInicial.getX()-anchoHitbox, posInicial.getY()-alturaHitbox, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaHitbox);

        return new TrollHueso(m, t, hitbox, salud, rango, damage, avancePorSegundo, tiempoAtaque, tiempoMuerte);
    }

    @Override
    protected GraficaEnemigo crearGrafica() {
        return GeneradorGraficaEnemigos.crearGrafica(this, miTablero.getGrafica());
    }

    @Override
    public void aceptar(VisitanteEnemigos v) {
        v.visitar(this);
    }

    @Override
    protected Sonido getSonidoImpacto() {
        return Sonido.GOLPE_TROLL2;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return Sonido.MUERTE_ORCO;
    }
}