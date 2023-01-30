package logica.entidades.defensores;

import grafica.entidades.GeneradorGraficaDefensores;
import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import util.Sonido;
import logica.entidades.VisitanteDefensores;
import util.config.ConfigDoubleEntidades;
import util.config.ConfigIntEntidades;
import util.config.LectorConfiguracion;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ElfoMele extends DefensorMele {
    protected ElfoMele(Motor m, Bloque b, Tablero t, Rectangle2D hitbox, Rectangle2D rango, int salud, int damage, int tiempoAtaque) {
        super(m,b,t,hitbox,rango,salud,damage,tiempoAtaque);
    }

    public static ElfoMele create(Motor m, Bloque b, Tablero t){
        int salud = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.SALUD_ELFO_MELE);
        double anchoHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_HITBOX_ELFO_MELE);
        double alturaHitbox = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ALTO_HITBOX_ELFO_MELE);
        double anchoRango = LectorConfiguracion.getInstance().getConfig(ConfigDoubleEntidades.ANCHO_RANGO_ELFO_MELE);
        double alturaRango = alturaHitbox;
        int tiempoAtaque = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.TIEMPO_ATAQUE_ELFO_MELE);

        Rectangle2D hitbox = new Rectangle2D.Double(t.getBoundsBloque(b).getCenterX()-anchoHitbox/2 , t.getBoundsBloque(b).getCenterY()-alturaHitbox/2, anchoHitbox, alturaHitbox);
        Rectangle2D rango= new Rectangle2D.Double(0,0,anchoRango,alturaRango);

        int damage = LectorConfiguracion.getInstance().getConfig(ConfigIntEntidades.DAMAGE_ELFO_MELE);

        return new ElfoMele(m, b, t,hitbox, rango, salud, damage, tiempoAtaque);
    }

    @Override
    public void aceptar(VisitanteDefensores v) {
        v.visitar(this);
    }

    @Override
    protected GraficaDefensor crearGrafica() {
        return GeneradorGraficaDefensores.crearGrafica(this,miTablero.getGrafica());
    }

    private int nroRandomRango() {
        Random random = new Random();
        return random.nextInt(3 - 1) + 1;
    }
    @Override
    protected Sonido getSonidoImpacto() {
        int numeroSonido = nroRandomRango();
        if (numeroSonido==1){
            return Sonido.ESPADA_CABALLERO1;
        }
        else
            return Sonido.ESPADA_CABALLERO2;
    }

    @Override
    protected Sonido getSonidoMuerte() {
        return null;
    }
}