package logica.entidades;

import logica.Tablero;
import util.ReproductorSonidos;
import util.Sonido;

import java.awt.geom.Rectangle2D;

public abstract class Entidad{
    protected final Tablero miTablero;
    protected final Rectangle2D miHitbox;
    protected Entidad(Tablero t, Rectangle2D hitbox) {
        miTablero = t;
        miHitbox = hitbox;
    }

    public Rectangle2D getHitbox() {
        return miHitbox;
    }

    public abstract void actuar(int tiempo);
    protected void reproducirSonido(Sonido s){
        if (s != null) {
            ReproductorSonidos.getInstance().reproducirSFX(s);
        }
    }
}