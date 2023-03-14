package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    Parada findByNombre(String nombre);

    Parada findById(long idParada);

}
