package logica.niveles.estrategias;

import logica.Juego;
import logica.niveles.FabricaEntidadesModoPuente;
import util.config.ConfigDoubleTablero;
import util.config.ConfigIntNiveles;
import util.config.LectorConfiguracion;

import java.awt.geom.Point2D;

public abstract class EstrategiaModoPuente extends EstrategiaJuego {
    protected int tiempoDesdeMoneda;
    protected final int tiempoHastaMoneda;

    public EstrategiaModoPuente(
        Juego j,
        int cantEnemigos1,
        int cantEnemigos2,
        int cantEnemigos3,
        double probabilidadPareja,
        int tiempoHastaEnemigo,
        int tiempoNotificarHorda
    )
    {
        super(
            j,
            new FabricaEntidadesModoPuente(),
            cantEnemigos1,
            cantEnemigos2,
            cantEnemigos3,
            probabilidadPareja,
            tiempoHastaEnemigo,
            tiempoNotificarHorda
        );

        tiempoDesdeMoneda = 0;
        tiempoHastaMoneda = LectorConfiguracion.getInstance().getConfig(ConfigIntNiveles.TIEMPO_HASTA_MONEDA);
    }

    @Override
    public void avanzar(int tiempo) {
        super.avanzar(tiempo);
        tiempoDesdeMoneda += tiempo;

        if (tiempoDesdeMoneda >= tiempoHastaMoneda) {
            tiempoDesdeMoneda -= tiempoHastaMoneda;
            generarMoneda();
        }
    }

    protected void generarMoneda() {
        double x = rand.nextDouble(
                miJuego.getTablero().getAncho() - LectorConfiguracion.getInstance().getConfig(ConfigDoubleTablero.ANCHO_MONEDA)
        );
        double y = rand.nextDouble(
                miJuego.getTablero().getAlto() - LectorConfiguracion.getInstance().getConfig(ConfigDoubleTablero.ALTO_MONEDA)
        );
        Point2D point = new Point2D.Double(x, y);
        miJuego.crearMoneda(point);
    }
}