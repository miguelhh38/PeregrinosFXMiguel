package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarnetRepository extends JpaRepository<Carnet, Long> {


}
