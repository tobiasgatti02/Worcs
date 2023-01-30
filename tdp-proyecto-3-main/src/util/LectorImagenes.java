package util;

import logica.ModoJuego;
import util.config.ConfigStringImagenes;
import util.config.LectorConfiguracion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LectorImagenes {
    private final ConcurrentMap<String, Image> imagenes;
    private final ConcurrentHashMap<Par<String, Dimension>, Image> imagenesRedimensionadas;
    private static LectorImagenes instance;
    private ModoJuego modoPrecargado;

    public static LectorImagenes getInstance() {
        if(instance == null)
            instance = new LectorImagenes();
        return instance;
    }

    private LectorImagenes(){
        imagenes = new ConcurrentHashMap<>();
        imagenesRedimensionadas = new ConcurrentHashMap<>();
        modoPrecargado = null;
    }

    public Image getImagen(String ruta, Dimension tamano){
        if(ruta == null)
            throw new RuntimeException("Ruta nula");

        Image imagenRedimensionada =  imagenesRedimensionadas.get(new Par<>(ruta, tamano));
        if(imagenRedimensionada == null){
            Image imagen = imagenes.get(ruta);
            if(imagen == null){
                try {
                    imagen = leerImagen(ruta);
                    if(imagen != null)
                        imagenes.put(ruta, imagen);
                } catch (IOException | IllegalArgumentException e) {
                    throw new RuntimeException("Error al leer imagen " + ruta);
                }
            }
            imagenRedimensionada = redimensionarImagen(imagen, tamano);
            imagenesRedimensionadas.put(new Par<>(ruta,tamano), imagenRedimensionada);
        }
        return imagenRedimensionada;
    }

    private Image redimensionarImagen(Image imagen, Dimension tamano) {
        //Mejora sustancialmente el rendimiento con respecto a getScaledInstance
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();
        BufferedImage toReturn;
        if (tamano.height > 0 && tamano.width > 0) {
            toReturn= config.createCompatibleImage(tamano.width, tamano.height, Transparency.TRANSLUCENT);

            Graphics g = toReturn.getGraphics();
            g.drawImage(imagen,0,0,tamano.width,tamano.height,null);
            g.dispose();
        }
        else
            toReturn = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        return toReturn;
    }
    private Image leerImagen(String ruta) throws IOException{
        Image toReturn;
        URL res = getClass().getResource(ruta);
        if(res == null)
            throw new IOException("La imagen solicitada no existe: " + ruta);
        if(ruta.toLowerCase().endsWith(".gif")) {
            toReturn = new ImageIcon(res).getImage();
        } else{
            toReturn = ImageIO.read(res);
        }
        if(toReturn == null)
            System.err.println("Error al leer imagen " + ruta);
        return toReturn;
    }

    private void precargar(String img){
        try {
            imagenes.put(img, leerImagen(img));
        } catch (IOException e) {
            System.err.println("Error al leer imagen " + img);
            e.printStackTrace();
        }
    }
    public void precargarImagenes(ModoJuego m){
        if(m != modoPrecargado) {
            String ruta = null;
            String linea;
            imagenes.clear();
            switch (m) {
                case ModoPuente ->
                        ruta = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.RUTA_PRECARGA_PUENTE);
                case ModoCastillo ->
                        ruta = LectorConfiguracion.getInstance().getConfig(ConfigStringImagenes.RUTA_PRECARGA_CASTILLO);
            }
            //Formatos: CANT;CARPETA
            //          RUTACOMPLETA.png

            try (BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(ruta)))) {
                linea = r.readLine();
                while (linea != null) {
                    if (!linea.isBlank()) {
                        procesarLineaPrecarga(linea);
                    }
                    linea = r.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            modoPrecargado = m;
        }
    }

    private void procesarLineaPrecarga(String linea) {
        String rutaImagen;
        int cantImagenes;
        String[] tokens;
        tokens = linea.split(";");
        if(tokens.length == 1) {
            precargar(tokens[0]);
        }
        else{
            cantImagenes = Integer.parseInt(tokens[0]);
            rutaImagen = tokens[1].trim();
            if(!rutaImagen.startsWith("/"))
                rutaImagen = "/"+ rutaImagen;
            if(!rutaImagen.endsWith("/"))
                rutaImagen += "/";
            for (int i = 0; i < cantImagenes; i++) {
                precargar(rutaImagen + i + ".png");
            }
        }
    }
}
