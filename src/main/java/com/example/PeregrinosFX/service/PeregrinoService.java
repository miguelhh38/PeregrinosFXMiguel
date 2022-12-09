package com.example.PeregrinosFX.service;

import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.generic.GenericService;

public interface PeregrinoService extends GenericService <Peregrino> {

    public abstract Peregrino addPeregrino(Peregrino peregrino);

}
