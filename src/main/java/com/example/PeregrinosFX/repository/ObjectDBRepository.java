package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.EnvioACasa;

import javax.persistence.*;
import java.util.List;

public class ObjectDBRepository {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb.odb");
    public static EntityManager em = emf.createEntityManager();

}
