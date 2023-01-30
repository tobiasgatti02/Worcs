package main;

import grafica.Ventana;

import javax.swing.*;

public class launcher {
    // impide que se creen instancias de la clase
    private launcher() {
        throw new AssertionError();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");
        SwingUtilities.invokeLater(() -> {
           Ventana ventana = new Ventana();
        });
    }
}
