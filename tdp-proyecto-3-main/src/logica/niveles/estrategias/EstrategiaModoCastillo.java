package logica.niveles.estrategias;

import logica.Juego;
import logica.niveles.FabricaEntidadesModoCastillo;

public abstract class EstrategiaModoCastillo extends EstrategiaJuego {
    public EstrategiaModoCastillo(
            Juego j,
            int cantEnemigos1,
            int cantEnemigos2,
            int cantEnemigos3,
            double probabilidadPareja,
            int tiempoHastaEnemigo,
            int tiempoNotificarHorda
    ) {
        super(
            j,
            new FabricaEntidadesModoCastillo(),
            cantEnemigos1,
            cantEnemigos2,
            cantEnemigos3,
            probabilidadPareja,
            tiempoHastaEnemigo,
            tiempoNotificarHorda
        );
    }
}