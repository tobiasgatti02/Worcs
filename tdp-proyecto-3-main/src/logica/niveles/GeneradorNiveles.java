package logica.niveles;

import logica.ModoJuego;
import logica.Juego;
import logica.niveles.estrategias.EstrategiaPuente1;
import logica.niveles.estrategias.EstrategiaPuente2;
import logica.niveles.estrategias.EstrategiaCastillo1;
import logica.niveles.estrategias.EstrategiaCastillo2;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;

public class GeneradorNiveles {
    protected static GeneradorNiveles instance;

    public static GeneradorNiveles getInstance() {
        if (instance == null)
            instance = new GeneradorNiveles();
        return instance;
    }

    public Nivel generarNivel(int n, ModoJuego m, Juego j) {
        Nivel niv = null;
        if(m == ModoJuego.ModoPuente){
            if (n == 1){
                niv = new Nivel(n, new EstrategiaPuente1(j), new FabricaEntidadesModoPuente(), LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.MONEDAS_INICIALES_PUENTE_1));
            }
            else if (n == 2){
                niv = new Nivel(n, new EstrategiaPuente2(j), new FabricaEntidadesModoPuente(), LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.MONEDAS_INICIALES_PUENTE_2));
            }
        }
        if (m == ModoJuego.ModoCastillo){
            if (n == 1){
                niv = new Nivel(n, new EstrategiaCastillo1(j), new FabricaEntidadesModoCastillo(), LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.MONEDAS_INICIALES_CASTILLO_1));
            }
            else if (n == 2){
                niv = new Nivel(n, new EstrategiaCastillo2(j), new FabricaEntidadesModoCastillo(), LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.MONEDAS_INICIALES_CASTILLO_2));
            }
        }
        return niv;
    }

    public int cantNiveles(ModoJuego m){
        int toReturn;
        switch (m){
            case ModoPuente, ModoCastillo -> toReturn = 2;
            default -> toReturn = 0;
        }
        return toReturn;
    }
}