package com.example.PeregrinosFX.bean;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "peregrinos")
public class Peregrino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPeregrino", updatable = false, nullable = false)

    private long idPeregrino;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "nacionalidad")
    private String nacionalidad;


    @OneToOne
    @JoinColumn(name = "id_carnet")
    private Carnet carnet;


    @OneToMany(mappedBy = "peregrino")
    private List<Estancia> estancias;

    @ManyToMany
    @JoinColumn(name ="paradas")
    private List<Parada> paradas;
    public Peregrino() {
    }

    public long getIdPeregrino() {
        return idPeregrino;
    }

    public void setIdPeregrino(long idPeregrino) {
        this.idPeregrino = idPeregrino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }


    public List<Estancia> getEstancias() {
        return estancias;
    }

    public void setEstancias(List<Estancia> estancias) {
        this.estancias = estancias;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    @Override
    public String toString() {
        return "Peregrino{" +
                "idPeregrino=" + idPeregrino +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", carnet=" + carnet +
                '}';
    }

    public String peregrinoData() {
        String ret = "";
        ret = "Id Peregrino: " + idPeregrino + " | Nombre: " + nombre + " | Nacionalidad: " + nacionalidad;
        return ret;
    }
}
