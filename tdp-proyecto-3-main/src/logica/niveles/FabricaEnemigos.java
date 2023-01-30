package logica.niveles;

import logica.Motor;
import logica.Tablero;
import logica.entidades.enemigos.Enemigo;

import java.awt.geom.Point2D;

public interface FabricaEnemigos {
    Enemigo crearEnemigo1(Motor m, Point2D posInicial, Tablero t);
    Enemigo crearEnemigo2(Motor m, Point2D posInicial, Tablero t);
    Enemigo crearEnemigo3(Motor m, Point2D posInicial, Tablero t);
}