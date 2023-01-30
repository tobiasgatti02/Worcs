package logica.niveles;

import logica.niveles.estrategias.EstrategiaJuego;

public class Nivel {
    private final int monedasIniciales;
    protected final int numero;
    protected final EstrategiaJuego miEstrategia;
    protected final FabricaCartas miFabricaCartas;

    public Nivel(int numero, EstrategiaJuego estrategia, FabricaCartas fabrica, int monedasIniciales) {
        this.monedasIniciales = monedasIniciales;
        miFabricaCartas = fabrica;
        miEstrategia = estrategia;
        this.numero  = numero;
    }

    public EstrategiaJuego getEstrategia() {
        return miEstrategia;
    }

    public FabricaCartas getFabricaCartas() {
        return miFabricaCartas;
    }

    public int getNumero() {
        return numero;
    }

    public int getMonedasIniciales() {
        return monedasIniciales;
    }
}