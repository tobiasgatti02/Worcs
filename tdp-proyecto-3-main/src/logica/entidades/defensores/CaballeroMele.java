package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.VisitanteDefensores;
import util.Sonido;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Rectangle2D;

public class CaballeroMele extends DefensorMele {
    protected CaballeroMele(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rango, int salud, int damage, int tiempoAtaque) {
        super(m,b,t,hitbox,rango,salud,damage,tiempoAtaque);
    }

    public static CaballeroMele create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_CABALLERO_MELE);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_CABALLERO_MELE);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_CABALLERO_MELE);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_CABALLERO_MELE);
        double alturaRango = alturaHitbox;
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_CABALLERO_MELE);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2 , t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        int damage = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DAMAGE_CABALLERO_MELE);

        return new CaballeroMele(m, b, t,hitbox, rango, salud, damage, tiempoAtaque);
    }

    @Override
    public void aceptar(VisitanteDefensores v) {
        v.visitar(this);
    }

    @Override
    protected GraficaDefensor crearGrafica() {
        return GeneradorGraficaDefensores.crearGrafica(this,miTablero.getGrafica());
    }

    @Override
    protected Sonido getSonidoImpacto() {
        return null;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return Sonido.MUERTE_DEFENSOR_NO_HADA;
    }
}