package com.example.PeregrinosFX.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Servicio implements Serializable {

    @Serial
    private static final long serialVersionUID = 7462669765849498386L;
    String nombre;
    double precio;
    ArrayList<Integer> paradas;

    public  Servicio() {

    }
    public Servicio(String nombre, double precio, ArrayList<Integer> paradas) {
        this.nombre = nombre;
        this.precio = precio;
        this.paradas = paradas;
    }

    @Override
    public String toString() {
        return  nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Integer> getParadas() {
        return paradas;
    }

    public void setParadas(ArrayList<Integer> paradas) {
        this.paradas = paradas;
    }


}
