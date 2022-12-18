package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Estancia;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.EstanciaRepository;
import com.example.PeregrinosFX.service.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstanciaServiceImp implements EstanciaService {

    @Autowired
    private EstanciaRepository estanciaRepository;

    @Override
    public Estancia save(Estancia entity) {
        return null;
    }

    @Override
    public Estancia update(Estancia entity) {
        return null;
    }

    @Override
    public void delete(Estancia entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Estancia> entities) {

    }

    @Override
    public Estancia find(Long id) {
        return estanciaRepository.findById(id).get();
    }

    @Override
    public List<Estancia> findAll() {
        return null;
    }


    @Override
    public Estancia addEstancia(Estancia estancia) {
        return estanciaRepository.save(estancia);
    }

    @Override
    public ArrayList<Estancia> findByParada(Parada parada) {
        return estanciaRepository.findByParada(parada);
    }

    @Override
    public ArrayList filtrarEstancias(Parada parada, LocalDate fech1, LocalDate fech2) {
        ArrayList<Estancia> estancias = findByParada(parada);
        ArrayList<Estancia> estancias1 = new ArrayList<>();
        for (Estancia e: estancias) {
            if (e.getFecha().isAfter(fech1) && e.getFecha().isBefore(fech2)) {
                estancias1.add(e);
            }
        }
        return estancias1;
    }

    public String mostrarEstancias(ArrayList<Estancia> estancias) {
        String ret = "";
        if (estancias.isEmpty()) {
            ret = "No hay estancias en el rango de fechas selecionado";
        }
        for (Estancia e: estancias) {
            ret += "Id Estancia: " + e.getIdEstancia() + " | Fecha: " + e.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Id peregrino: " + e.getPeregrino().getIdPeregrino() + " | Parada: " + e.getParada().getNombre() + "\n";
        }

        return ret;
    }

}
