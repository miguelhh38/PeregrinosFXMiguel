package com.example.PeregrinosFX.service;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.generic.GenericService;


public interface ParadaService extends GenericService <Parada> {

    Parada findByNombre(String nombre);
}
