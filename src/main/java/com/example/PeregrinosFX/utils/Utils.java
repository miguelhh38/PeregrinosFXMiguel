package com.example.PeregrinosFX.utils;

import java.util.InputMismatchException;

public class Utils {

    public static boolean leerInt(String numero) {
        boolean correcto = false;
        try {
                Integer.parseInt(numero);
                correcto = true;
            } catch (NumberFormatException ime) {
                correcto = false;
            }
        return correcto;
    }

    public static boolean leerDouble(String numero) {
        boolean correcto = false;
        try {
            Double.parseDouble(numero);
            correcto = true;
        } catch (NumberFormatException ime) {
            correcto = false;
        }
        return correcto;
    }

}
