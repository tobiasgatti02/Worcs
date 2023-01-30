package logica.niveles;

import logica.Motor;
import logica.Tablero;
import logica.cartas.*;
import logica.entidades.enemigos.Enemigo;
import logica.entidades.enemigos.OrcoHacha;
import logica.entidades.enemigos.OrcoMazo;
import logica.entidades.enemigos.OrcoEspada;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class FabricaEntidadesModoPuente implements FabricaEnemigos, FabricaCartas {
    @Override
    public Enemigo crearEnemigo1(Motor m, Point2D posInicial, Tablero t) {
        return OrcoMazo.create(m, posInicial, t);
    }

    @Override
    public Enemigo crearEnemigo2(Motor m, Point2D posInicial, Tablero t) {
        return OrcoEspada.create(m, posInicial, t);
    }

    @Override
    public Enemigo crearEnemigo3(Motor m, Point2D posInicial, Tablero t) {
        return OrcoHacha.create(m, posInicial, t);
    }

    @Override
    public Iterable<Carta> crearCartas() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new CartaHada());
        cartas.add(new CartaArqueroElfo());
        cartas.add(new CartaElfoMele());
        cartas.add(new CartaMago());
        return cartas;
    }
}