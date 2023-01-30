package grafica.entidades;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Animador {
    protected static Animador instance;
    protected Map<GraficaEntidad, String> cambiosPendientes;
    protected Map<GraficaEntidad, String> cambiosAnteriores;
    protected final Timer miTimer;
    protected final AtomicBoolean hayCambios;
    protected final Object lockCambiosPendientes;
    protected Animador(){
        cambiosPendientes = new HashMap<>();
        cambiosAnteriores = new HashMap<>();
        miTimer = new Timer(1, e -> procesarCambios());
        hayCambios = new AtomicBoolean();
        miTimer.start();
        lockCambiosPendientes= new Object();
    }

    public static Animador getInstance() {
        if (instance == null) {
            instance = new Animador();
        }
        return instance;
    }

    protected void procesarCambios(){
        if(hayCambios.get()) {
            synchronized (lockCambiosPendientes) {
                Map<GraficaEntidad, String> aux = cambiosAnteriores;
                cambiosAnteriores = cambiosPendientes;
                cambiosPendientes = aux;
            }
            GraficaEntidad grafica;
            String img;
            for (Map.Entry<GraficaEntidad, String> ent : cambiosAnteriores.entrySet()) {
                grafica = ent.getKey();
                img = ent.getValue();
                grafica.establecerImagen(img);
            }
            cambiosAnteriores.clear();
        }
    }
    public void cambiarImagen(GraficaEntidad g, String img){
        synchronized (lockCambiosPendientes) {
            cambiosPendientes.put(g, img);
            hayCambios.set(true);
        }

    }
}
