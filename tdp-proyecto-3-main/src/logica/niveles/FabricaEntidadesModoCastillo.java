package logica.niveles;

import logica.Motor;
import logica.Tablero;
import logica.cartas.*;
import logica.entidades.enemigos.Enemigo;
import logica.entidades.enemigos.TrollHueso;
import logica.entidades.enemigos.TrollMazoPiedra;
import logica.entidades.enemigos.TrollTronco;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class FabricaEntidadesModoCastillo implements FabricaEnemigos, FabricaCartas {
    @Override
    public Enemigo crearEnemigo1(Motor m, Point2D posInicial, Tablero t) {
        return TrollTronco.create(m, posInicial, t);
    }

    @Override
    public Enemigo crearEnemigo2(Motor m, Point2D posInicial, Tablero t) {
        return TrollHueso.create(m, posInicial, t);
    }

    @Override
    public Enemigo crearEnemigo3(Motor m, Point2D posInicial, Tablero t) {
        return TrollMazoPiedra.create(m, posInicial, t);
    }

    @Override
    public Iterable<Carta> crearCartas() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new CartaHada());
        cartas.add(new CartaEscudero());
        cartas.add(new CartaArqueraHumana());
        cartas.add(new CartaCaballeroMele());
        return cartas;
    }
}