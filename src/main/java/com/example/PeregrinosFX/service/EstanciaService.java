package com.example.PeregrinosFX.service;


import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.generic.GenericService;

import java.time.LocalDate;
import java.util.ArrayList;

public interface EstanciaService extends GenericService<Estancia> {

    public abstract Estancia addEstancia(Estancia estancia);

    ArrayList<Estancia> findByParada(Parada parada);

    public ArrayList filtrarEstancias(Parada parada, LocalDate fech1, LocalDate fech2);
}
