package logica.niveles;

import logica.cartas.Carta;

public interface FabricaCartas {
    Iterable<Carta> crearCartas();
}