package com.example.PeregrinosFX.service;

import com.example.PeregrinosFX.bean.Carnet;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.generic.GenericService;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;

public interface PeregrinoService extends GenericService <Peregrino> {

    public abstract Peregrino addPeregrino(Peregrino peregrino);

    public abstract String exportarCarnetXML(Peregrino p);

    Peregrino findByCarnet(Carnet carnet);

}
