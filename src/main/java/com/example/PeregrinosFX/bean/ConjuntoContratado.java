package com.example.PeregrinosFX.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConjuntoContratado implements Serializable {

    @Serial
    private static final long serialVersionUID = 7462669765849498386L;

    //idEstancia
    private long id;

    private double precioTotal;

    private char modoPago;

    private ArrayList<Servicio> servicios;

    private String extra = null;

    public ConjuntoContratado() {
    }

    public ConjuntoContratado(long id, double precioTotal, char modoPago, String extra, ArrayList<Servicio> servicios) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
        this.extra = extra;
        this.servicios = servicios;
    }


    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public char getModoPago() {
        return modoPago;
    }

    public void setModoPago(char modoPago) {
        this.modoPago = modoPago;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "ConjuntoContratado{" +
                "id=" + id +
                ", precioTotal=" + precioTotal +
                ", modoPago=" + modoPago +
                ", servicios=" + servicios +
                ", extra=" + extra + '\'' +
                '}';
    }
}

