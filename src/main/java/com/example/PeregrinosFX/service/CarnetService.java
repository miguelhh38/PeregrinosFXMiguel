package com.example.PeregrinosFX.service;

import com.example.PeregrinosFX.bean.Carnet;
import com.example.PeregrinosFX.generic.GenericService;

public interface CarnetService extends GenericService<Carnet> {

    public abstract Carnet addCarnet(Carnet carnet);

}
