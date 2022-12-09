package com.example.PeregrinosFX.bean;


import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "carnets")
public class Carnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarnet", updatable = false, nullable = false)
    private long idCarnet;

    @Column (name = "fechaexp")
    private LocalDate fechaExp;

    @Column(name="distancia")
    private double distancia;

    @Column (name = "numvips")
    private int numVips;


    @OneToOne
    @JoinColumn(name = "idParada")
    private Parada paradaInicial;

    public Carnet() {

    }

    public long getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(long idPeregrino) {
        this.idCarnet = idCarnet;
    }

    public LocalDate getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(LocalDate fechaExp) {
        this.fechaExp = fechaExp;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getNumVips() {
        return numVips;
    }

    public void setNumVips(int numVips) {
        this.numVips = numVips;
    }


    public Parada getParadaInicial() {
        return paradaInicial;
    }

    public void setParadaInicial(Parada paradaInicial) {
        this.paradaInicial = paradaInicial;
    }

    @Override
    public String toString() {
        return "Carnet{" +
                "idCarnet=" + idCarnet +
                ", fechaExp=" + fechaExp +
                ", distancia=" + distancia +
                ", numVips=" + numVips +

                ", paradaInicial=" + paradaInicial +
                '}';
    }
}
