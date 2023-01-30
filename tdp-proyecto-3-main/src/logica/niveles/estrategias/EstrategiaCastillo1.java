package logica.niveles.estrategias;

import logica.Juego;
import logica.Tablero;
import util.config.ConfigDoubleNiveles;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaCastillo1 extends EstrategiaModoCastillo {
    public EstrategiaCastillo1(Juego j) {
        super(
            j,
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_1_NIVEL_CASTILLO_1),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_2_NIVEL_CASTILLO_1),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_3_NIVEL_CASTILLO_1),
            LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.PROBABILIDAD_PAREJA),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_HASTA_ENEMIGO_NIVEL_CASTILLO_1),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_NOTIFICAR_HORDA_NIVEL_CASTILLO_1)
        );
    }

    @Override
    protected void crearHorda(int horda) {
        crearHorda1();
    }

    @Override
    protected List<Integer> getHordas() {
        List<Integer> toRet = new ArrayList<>();
        toRet.add(LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.ENEMIGOS_PARA_HORDA_CASTILLO_1));
        return toRet;
    }

    private void crearHorda1() {
        Tablero tablero = miJuego.getTablero();

        double x = tablero.getAncho()*1.1;
        double offsetHorda = LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.OFFSET_HORDA);

        // primera columna
        crearEnemigo(2, 0, x);
        crearEnemigo(3, 1, x);
        crearEnemigo(3, 2, x);
        crearEnemigo(3, 3, x);
        crearEnemigo(3, 4, x);
        crearEnemigo(2, 5, x);

        // segunda columna
        crearEnemigo(2, 0, x+offsetHorda);
        crearEnemigo(1, 1, x+offsetHorda);
        crearEnemigo(1, 2, x+offsetHorda);
        crearEnemigo(1, 3, x+offsetHorda);
        crearEnemigo(1, 4, x+offsetHorda);
        crearEnemigo(2, 5, x+offsetHorda);
    }
}