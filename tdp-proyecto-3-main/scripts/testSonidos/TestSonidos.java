//import com.adonax.audiocue.AudioCue;
//import grafica.Ventana;
//import util.ReproductorSonidos;
//import util.Sonido;
//import util.config.ConfigStringSonidos;
//import util.config.LectorConfiguracion;
//
//public class TestSonidos {
//    public static void main(String[] args) {
//        AudioCue audio;
//        for (Sonido s : Sonido.values()) {
//            ConfigStringSonidos configRuta = s.getConfigRuta();
//            String ruta = LectorConfiguracion.getInstance().getConfig(configRuta);
//            audio = AudioCue.makeStereoCue(ReproductorSonidos.class.getResource(ruta))
//        }
//    }
//}
