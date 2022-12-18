package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EstanciaRepository extends JpaRepository<Estancia, Long> {

    ArrayList<Estancia> findByParada(Parada parada);
}
