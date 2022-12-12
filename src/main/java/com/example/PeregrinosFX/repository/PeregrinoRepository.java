package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.Peregrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeregrinoRepository extends JpaRepository<Peregrino, Long> {

}
