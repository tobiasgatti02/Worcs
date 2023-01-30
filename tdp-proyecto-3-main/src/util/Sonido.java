package util;

import util.config.ConfigIntSonidos;
import util.config.ConfigStringSonidos;

public enum Sonido {
    MUSICA_PUENTE(ConfigStringSonidos.RUTA_MUSICA_PUENTE, ConfigIntSonidos.POLIFONIA_MUSICA_PUENTE),
    MUSICA_MENU(ConfigStringSonidos.RUTA_MUSICA_MENU, ConfigIntSonidos.POLIFONIA_MUSICA_MENU),
    MUSICA_CASTILLO(ConfigStringSonidos.RUTA_MUSICA_CASTILLO, ConfigIntSonidos.POLIFONIA_MUSICA_CASTILLO),
    FLECHAZO_HUMANA(ConfigStringSonidos.RUTA_FLECHAZO_HUMANA, ConfigIntSonidos.POLIFONIA_SFX),
    MUERTE_ORCO(ConfigStringSonidos.RUTA_MUERTE_ORCO, ConfigIntSonidos.POLIFONIA_SFX),
    ESPADA_CABALLERO1(ConfigStringSonidos.RUTA_ESPADAZO_CABALLERO1, ConfigIntSonidos.POLIFONIA_SFX),

    PALA(ConfigStringSonidos.RUTA_SONIDO_PALA, ConfigIntSonidos.POLIFONIA_SFX),

    GOLPE_TROLL(ConfigStringSonidos.RUTA_GOLPE_TROLL, ConfigIntSonidos.POLIFONIA_SFX),

    HADA_MONEDA(ConfigStringSonidos.RUTA_HADA_MONEDA, ConfigIntSonidos.POLIFONIA_SFX),

    RECOGER_MONEDA(ConfigStringSonidos.RUTA_RECOGER_MONEDA, ConfigIntSonidos.POLIFONIA_SFX),

    GOLPE_TROLL2(ConfigStringSonidos.RUTA_GOLPE_TROLL, ConfigIntSonidos.POLIFONIA_SFX),

    ESPADA_ENEMIGO(ConfigStringSonidos.RUTA_ESPADA_ENEMIGO, ConfigIntSonidos.POLIFONIA_SFX),

    DISPARO_MAGO(ConfigStringSonidos.RUTA_DISPARO_MAGO, ConfigIntSonidos.POLIFONIA_SFX),
    CARTA_SELECCIONADA(ConfigStringSonidos.RUTA_CARTA_SELECCIONADA, ConfigIntSonidos.POLIFONIA_SFX),
    DEFENSOR_COLOCADO(ConfigStringSonidos.RUTA_DEFENSOR_COLOCADO, ConfigIntSonidos.POLIFONIA_SFX),
    ESPADA_CABALLERO2(ConfigStringSonidos.RUTA_ESPADAZO_CABALLERO2, ConfigIntSonidos.POLIFONIA_SFX),
    MUERTE_HADA(ConfigStringSonidos.RUTA_MUERTE_HADA, ConfigIntSonidos.POLIFONIA_SFX),
    MUERTE_DEFENSOR_NO_HADA(ConfigStringSonidos.RUTA_MUERTE_DEFENSOR_NO_HADA, ConfigIntSonidos.POLIFONIA_SFX),
    MAZO_GOLPE(ConfigStringSonidos.RUTA_MAZO_GOLPE, ConfigIntSonidos.POLIFONIA_SFX),
    GAME_OVER(ConfigStringSonidos.GAME_OVER, ConfigIntSonidos.POLIFONIA_SFX),
    NOTIFICAR_HORDA(ConfigStringSonidos.NOTIFICAR_HORDA, ConfigIntSonidos.POLIFONIA_SFX);
    private final ConfigStringSonidos configRuta;
    private final ConfigIntSonidos configPolifonia;

    Sonido(ConfigStringSonidos r, ConfigIntSonidos p){
        configRuta = r;
        configPolifonia = p;
    }

    public ConfigStringSonidos getConfigRuta() {
        return configRuta;
    }

    public ConfigIntSonidos getConfigPolifonia() {
        return configPolifonia;
    }
}
