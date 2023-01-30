package logica.entidades;

import logica.entidades.enemigos.*;

public interface VisitanteEnemigos {
   void visitar(OrcoMazo e);
   void visitar(OrcoEspada e);
   void visitar(OrcoHacha e);
   void visitar(TrollTronco e);
   void visitar(TrollHueso e);
   void visitar(TrollMazoPiedra e);
}
