package com.example.PeregrinosFX.bean;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "estancias")
public class Estancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstancia", updatable = false, nullable = false)
    private long idEstancia;

    @Column (name = "fecha")
    private LocalDate fecha;

    @Column (name = "vip")
    private boolean vip = false;

    @OneToOne
    @JoinColumn(name = "idParada")
    private Parada parada;


    @ManyToOne
    @JoinColumn(name = "idPeregrino")
    private Peregrino peregrino;


    public Estancia(){

    }
    public long getIdEstancia() {
        return idEstancia;
    }

    public void setIdEstancia(long idEstancia) {
        this.idEstancia = idEstancia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public Peregrino getPeregrino() {
        return peregrino;
    }

    public void setPeregrino(Peregrino peregrino) {
        this.peregrino = peregrino;
    }

    @Override
    public String toString() {
        return "Estancia{" +
                "idEstancia=" + idEstancia +
                ", fecha=" + fecha +
                ", vip=" + vip +
                ", parada=" + parada +
                ", peregrino=" + peregrino +
                '}';
    }
}
