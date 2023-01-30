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

public class OrcoMazo extends Enemigo {
    protected OrcoMazo(Motor m, Tablero t, Rectangle2D hitbox, int salud, Rectangle2D rango, int damage, double avancePorSegundo, int tiempoAtaque, int tiempoMuerte) {
        super(m, t, hitbox, salud, rango, damage, avancePorSegundo, tiempoAtaque, tiempoMuerte);
    }

    public static OrcoMazo create(Motor m, Point2D posInicial, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_ORCO_MAZO);

        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ORCO_MAZO);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ORCO_MAZO);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_ORCO_MAZO);
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_ORCO_MAZO);
        int damage = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DAMAGE_ORCO_MAZO);
        double avancePorSegundo = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.AVANCE_POR_SEGUNDO_ORCO_MAZO);
        int tiempoMuerte = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_MUERTE_ORCO_MAZO);;

        Rectangle2D hitbox = new Rectangle2D.Double(posInicial.getX()-anchoHitbox, posInicial.getY()-alturaHitbox, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaHitbox);

        return new OrcoMazo(m, t, hitbox, salud, rango, damage, avancePorSegundo, tiempoAtaque, tiempoMuerte);
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
        return Sonido.MAZO_GOLPE;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return Sonido.MUERTE_ORCO;
    }
}