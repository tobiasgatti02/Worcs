package logica.niveles.estrategias;

import logica.Juego;
import logica.Tablero;
import util.config.ConfigDoubleNiveles;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaPuente2 extends EstrategiaModoPuente {
    public EstrategiaPuente2(Juego j) {
        super(
            j,
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_1_NIVEL_PUENTE_2),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_2_NIVEL_PUENTE_2),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.CANT_ENEMIGO_3_NIVEL_PUENTE_2),
            LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.PROBABILIDAD_PAREJA),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_HASTA_ENEMIGO_NIVEL_PUENTE_2),
            LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_NOTIFICAR_HORDA_NIVEL_PUENTE_2)
        );
    }

    @Override
    protected void crearHorda(int horda) {
        switch (horda) {
            case 1 -> crearHorda1();
            case 2 -> crearHorda2();
        }
    }

    @Override
    protected List<Integer> getHordas() {
        List<Integer> toRet = new ArrayList<>();
        toRet.add(LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.ENEMIGOS_PARA_HORDA_1_PUENTE_2));
        toRet.add(LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.ENEMIGOS_PARA_HORDA_2_PUENTE_2));
        return toRet;
    }

    private void crearHorda1() {
        Tablero tablero = miJuego.getTablero();

        double x = tablero.getAncho()*1.1;
        double offsetHorda = LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.OFFSET_HORDA);

        // primera columna
        crearEnemigo(3,0, x);
        crearEnemigo(2,2, x);
        crearEnemigo(2,3, x);
        crearEnemigo(3,5, x);

        // segunda columna
        crearEnemigo(1,0,x+offsetHorda);
        crearEnemigo(3,1,x+offsetHorda);
        crearEnemigo(2,2,x+offsetHorda);
        crearEnemigo(2,3,x+offsetHorda);
        crearEnemigo(3,4,x+offsetHorda);
        crearEnemigo(1,5,x+offsetHorda);

        // tercera columna
        crearEnemigo(1,1,x+(2*offsetHorda));
        crearEnemigo(2,2,x+(2*offsetHorda));
        crearEnemigo(2,3,x+(2*offsetHorda));
        crearEnemigo(1,4,x+(2*offsetHorda));
    }

    private void crearHorda2() {
        Tablero tablero = miJuego.getTablero();

        double x = tablero.getAncho()*1.1;
        double offsetHorda = LectorConfiguracion.getInstance().getConfig(ConfigDoubleNiveles.OFFSET_HORDA);

        // primera columna
        crearEnemigo(2,2, x);
        crearEnemigo(2,3, x);

        // segunda columna
        crearEnemigo(3,0,x+offsetHorda);
        crearEnemigo(3,1,x+offsetHorda);
        crearEnemigo(1,2,x+offsetHorda);
        crearEnemigo(1,3,x+offsetHorda);
        crearEnemigo(3,4,x+offsetHorda);
        crearEnemigo(3,5,x+offsetHorda);

        // tercera columna
        crearEnemigo(2,0,x+(2*offsetHorda));
        crearEnemigo(2,1,x+(2*offsetHorda));
        crearEnemigo(1,2,x+(2*offsetHorda));
        crearEnemigo(1,3,x+(2*offsetHorda));
        crearEnemigo(2,4,x+(2*offsetHorda));
        crearEnemigo(2,5,x+(2*offsetHorda));

        // cuarta columna
        crearEnemigo(1,0,x+(3*offsetHorda));
        crearEnemigo(1,1,x+(3*offsetHorda));
        crearEnemigo(1,2,x+(3*offsetHorda));
        crearEnemigo(1,3,x+(3*offsetHorda));
        crearEnemigo(1,4,x+(3*offsetHorda));
        crearEnemigo(1,5,x+(3*offsetHorda));

        // quinta columna
        crearEnemigo(2,2,x+(4*offsetHorda));
        crearEnemigo(2,3,x+(4*offsetHorda));
    }
}