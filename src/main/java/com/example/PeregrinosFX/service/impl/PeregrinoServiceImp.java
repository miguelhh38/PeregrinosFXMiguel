package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.repository.PeregrinoRepository;
import com.example.PeregrinosFX.service.PeregrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeregrinoServiceImp implements PeregrinoService {

    @Autowired
    private PeregrinoRepository peregrinoRepository;

    @Override
    public Peregrino save(Peregrino entity) {
        return null;
    }

    @Override
    public Peregrino update(Peregrino entity) {
        return null;
    }

    @Override
    public void delete(Peregrino entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Peregrino> entities) {

    }

    @Override
    public Peregrino find(Long id) {
        return peregrinoRepository.findById(id).get();
    }

    @Override
    public List<Peregrino> findAll() {
        return null;
    }

    @Override
    public Peregrino addPeregrino(Peregrino peregrino) {
        return peregrinoRepository.save(peregrino);
    }
}

