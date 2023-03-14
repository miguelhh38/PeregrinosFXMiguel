package com.example.PeregrinosFX.bean;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class EnvioACasa implements Serializable {

    @Serial
    private static final long serialVersionUID = 7462669765849498386L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private double peso;

    @Basic
    private int volumen;

    @Basic
    private boolean urgente;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Direccion direccion;

    @Basic
    private long idParada;

    public EnvioACasa() {

    }

    public EnvioACasa(long id, double peso, int volumen, boolean urgente, Direccion direccion, long idParada) {
        this.id = id;
        this.peso = peso;
        this.volumen = volumen;
        this.urgente = urgente;
        this.direccion = direccion;
        this.idParada = idParada;
    }
    public EnvioACasa(double peso, int volumen, boolean urgente, Direccion direccion, long idParada) {
            this.peso = peso;
        this.volumen = volumen;
        this.urgente = urgente;
        this.direccion = direccion;
        this.idParada = idParada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public long getParada() {
        return idParada;
    }

    public void setParada(long parada) {
        this.idParada = idParada;
    }

    @Override
    public String toString() {
        return "ID= " + id +
                " | Direccion= " + direccion.getDireccion() + ", " + direccion.getLocalidad() +
                " | Volumen= " + volumen + " | Peso= " + peso + " | Urgente= " + urgente;
    }
}
