package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.ParadaRepository;
import com.example.PeregrinosFX.service.ParadaService;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ParadaServiceImp implements ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;
    @Override
    public Parada save(Parada entity) {
        return null;
    }

    @Override
    public Parada update(Parada entity) {
        return null;
    }

    @Override
    public void delete(Parada entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Parada> entities) {

    }

    @Override
    public Parada find(Long id) {
        return null;
    }

    @Override
    public List<Parada> findAll() {
        return paradaRepository.findAll();
    }

    @Override
    public Parada findByNombre(String nombre) {
        return paradaRepository.findByNombre(nombre);
    }

    @Override
    public Parada findById(long idParada) {
        return paradaRepository.findById(idParada);
    }

    public ArrayList<Integer> guardarID(ObservableList nombre) {
        ArrayList<Integer> listaIdsParada = new ArrayList<>();

        for (int i= 0;i < nombre.size(); i++) {
            listaIdsParada.add((int) findByNombre(nombre.get(i).toString()).getIdParada());
        }
        return listaIdsParada;
    }

    public String buscarParadas(ArrayList<Integer> paradas) {
        String ret = "";
        for (int i=0; i<paradas.size(); i++) {
            ret += findById(paradas.get(i)).getNombre() + " \n";

        }
        return ret;
    }


}
