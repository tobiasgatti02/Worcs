package logica.entidades;

import logica.entidades.proyectiles.FlechaElfo;
import logica.entidades.proyectiles.FlechaHumana;
import logica.entidades.proyectiles.Orbe;

public interface VisitanteProyectiles {
    void visitar(FlechaElfo p);
    void visitar(FlechaHumana p);
    void visitar(Orbe p);
}