package util;
import com.adonax.audiocue.AudioCue;
import com.adonax.audiocue.AudioMixer;
import util.config.LectorConfiguracion;
import util.opcionesUsuario.AdministradorOpciones;
import util.opcionesUsuario.OpcionesDouble;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ReproductorSonidos {


    protected static ReproductorSonidos instance;
    protected final Map<String, AudioCue> sonidos;

    protected final Collection<Par<AudioCue,Integer>> loops;
    protected final AudioMixer mixerMusica;
    protected final AudioMixer mixerSFX;
    protected ReproductorSonidos() {
        {
            loops = new LinkedList<>();
            sonidos = new HashMap<>();
            mixerMusica = new AudioMixer();
            mixerSFX = new AudioMixer();
            try {
                mixerMusica.start();
                mixerSFX.start();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ReproductorSonidos getInstance(){
        if (instance == null)
            instance = new ReproductorSonidos();
        return instance;
    }


    public void reproducirSFX(Sonido sonido) {
        AudioCue cue = obtenerAudioCue(sonido);
        cue.play(mapearVolumen(AdministradorOpciones.getInstance().getOpcion(OpcionesDouble.VOLUMEN_SFX)));
    }

    public void reproducirMusica(Sonido sonido){
        AudioCue cue = obtenerAudioCue(sonido);

        int instance = cue.obtainInstance();
        if(instance < 0)
            throw new RuntimeException("Demasiados sonidos simultáneos");

        cue.start(instance);
        cue.setVolume(instance, mapearVolumen(AdministradorOpciones.getInstance().getOpcion(OpcionesDouble.VOLUMEN_MUSICA)));
        cue.setLooping(instance, -1);
        loops.add(new Par<>(cue, instance));
    }

    /*
        Mapea el volumen de escala lineal a exponencial con rango dinámico de 60dB
     */
    private double mapearVolumen(double vol) {
        return (vol == 0 ) ? 0 : Math.exp(vol * 6.908) / 1000.0;
    }

    protected AudioCue obtenerAudioCue(Sonido sonido){
        String ruta = LectorConfiguracion.getInstance().getConfig(sonido.getConfigRuta());
        boolean error = false;
        int polifonia;
        AudioCue toReturn = sonidos.get(ruta);
        if (toReturn == null) {
            polifonia =  LectorConfiguracion.getInstance().getConfig(sonido.getConfigPolifonia());
            URL resource = getClass().getResource(ruta);
            if(resource != null) {
                try {
                    toReturn = AudioCue.makeStereoCue(resource, polifonia);
                    toReturn.open(mixerSFX);
                    sonidos.put(ruta, toReturn);
                } catch (UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
                    error = true;
                }
            }
            else
                error = true;
            if(error)
                System.err.println("Error al leer audio: " + ruta);
        }
        return toReturn;
    }

    public void pararMusica(){
        for (Par<AudioCue, Integer> loop : loops) {
            loop.getE1().stop(loop.getE2());
            loop.getE1().releaseInstance(loop.getE2());
        }
        loops.clear();
    }

    public void establecerVolumenMusica(float vol) {
        for (Par<AudioCue, Integer> loop : loops) {
            loop.getE1().setVolume(loop.getE2(), mapearVolumen(vol));
        }
    }
}