package com.example.PeregrinosFX.bean;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Direccion implements Serializable {

    @Serial
    private static final long serialVersionUID = 7462669765849498386L;

    @Id
    @GeneratedValue
    private long id;

    @Basic
    private String direccion;

    @Basic
    private String localidad;


    public Direccion() {

    }

    public Direccion(String direccion, String localidad) {
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Direccion(long id, String direccion, String localidad) {
        this.id = id;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
