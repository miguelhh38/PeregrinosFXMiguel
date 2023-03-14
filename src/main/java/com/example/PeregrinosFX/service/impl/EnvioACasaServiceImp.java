package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.repository.ObjectDBRepository;
import com.example.PeregrinosFX.service.EnvioACasaService;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.PeregrinosFX.repository.ObjectDBRepository.em;

@Service
public class EnvioACasaServiceImp implements EnvioACasaService {

    public void addEnvio(EnvioACasa envioACasa) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb.odb");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(envioACasa);
            em.getTransaction().commit();
        }
        finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }


    }

    public List<EnvioACasa> findByParada(Long idParada) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb.odb");
        EntityManager em = emf.createEntityManager();

        //ObjectDBRepository.em.getTransaction().begin();
        TypedQuery<EnvioACasa> query = em.createQuery("SELECT e FROM EnvioACasa e WHERE e.idParada= " + idParada, EnvioACasa.class);

        List<EnvioACasa> result = query.getResultList();

        return result;

    }
}
