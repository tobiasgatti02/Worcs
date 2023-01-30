package logica.entidades;

import logica.entidades.defensores.*;

public interface VisitanteDefensores {
    void visitar(ArqueraHumana d);
    void visitar(ArqueroElfo d);
    void visitar(ElfoMele d);
    void visitar(CaballeroMele d);
    void visitar(Mago d);
    void visitar(Hada d);
    void visitar(Escudero d);
}
