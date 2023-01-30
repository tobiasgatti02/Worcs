package logica.entidades.defensores;

import grafica.entidades.GraficaDefensor;
import logica.Bloque;
import logica.Motor;
import logica.Tablero;
import logica.entidades.Entidad;
import util.Sonido;

import logica.entidades.VisitanteDefensores;
import java.awt.geom.Rectangle2D;

public abstract class Defensor extends Entidad {
    protected int salud;
    protected final Bloque miBloque;
    protected final Motor miMotor;
    protected GraficaDefensor miGrafica;

    protected Defensor(Motor m, int salud, Bloque b, Tablero t, Rectangle2D hitbox) {
        super(t,hitbox);
        //grafico se inicializa en el constructor de las subclases

        this.salud = salud;
        miMotor = m;
        miBloque = b;
        miBloque.ocupar(this);
    }

    protected void init(){
        miGrafica = crearGrafica();
        miMotor.agregarDefensor(this); //Debe ser la última línea (sincronización)
    }

    public void daniar(int d) {
        if(salud>0){
            salud = Math.max(0, salud - d);
            if (salud == 0){
                desaparecer();
                reproducirSonido(getSonidoMuerte());
            }
        }
    }

    public void desaparecer() {
        miMotor.eliminarDefensor(this);
        miBloque.desocupar(this);
        miGrafica.desaparecer();
    }

    @Override
    public abstract void actuar(int tiempo);

    public abstract void aceptar(VisitanteDefensores v);

    protected abstract GraficaDefensor crearGrafica();
    protected abstract Sonido getSonidoMuerte();
}
