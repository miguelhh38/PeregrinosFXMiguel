package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Direccion;
import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.controller.AlojarseController;
import com.example.PeregrinosFX.controller.RegistroController;
import com.example.PeregrinosFX.repository.ObjectDBRepository;
import com.example.PeregrinosFX.service.EnvioACasaService;
import com.example.PeregrinosFX.utils.Utils;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.PeregrinosFX.controller.LoginController.rol;
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
        } finally {
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

    public void realizarEnvio(TextField dirTEXT, TextField localidadTEXT, TextField sizeTEXT, TextField pesoTEXT, CheckBox urgenteCHECK) {
        String errores = "";
        if (dirTEXT.getText().length() < 3) {
            errores += "La dirección no es válida.\n";
        }
        if (localidadTEXT.getText().length() < 2) {
            errores += "La localidad no es válida.\n";
        }
        if (!Utils.leerInt(sizeTEXT.getText())) {
            errores += "Formato del tamaño incorrecto. Introduce numero entero.\n";
        }
        if (!Utils.leerDouble(pesoTEXT.getText())) {
            errores += "Formato del peso incorrecto. Introduce numero decimal (x.x).\n";
        }

        if (errores.isEmpty()) {
            if (rol == 2) {
                Direccion direccion = new Direccion(dirTEXT.getText(), localidadTEXT.getText());


                EnvioACasa envioACasa = new EnvioACasa(Double.parseDouble(pesoTEXT.getText()),
                        Integer.parseInt(sizeTEXT.getText()), urgenteCHECK.isSelected(), direccion,
                        RegistroController.usuarioActual.getParada().getIdParada());
                addEnvio(envioACasa);
                envioGuardado();
            }
            if (rol == 3) {
                Direccion direccion = new Direccion(dirTEXT.getText(), localidadTEXT.getText());

                EnvioACasa envioACasa = new EnvioACasa(Double.parseDouble(pesoTEXT.getText()),
                        Integer.parseInt(sizeTEXT.getText()), urgenteCHECK.isSelected(), direccion,
                        AlojarseController.paradaSel);
                addEnvio(envioACasa);
                envioGuardado();
            }
        } else {
            error(errores);
        }
    }

    private void envioGuardado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ENVIO GUARDADO");
        alert.setHeaderText(null);
        alert.setContentText("El envio ha sido guardado correctamente");
        Optional<ButtonType> result = alert.showAndWait();
    }

    private void error(String errores) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR AL GUARDAR ENVIO");
        alert.setHeaderText(null);
        alert.setContentText(errores);
        Optional<ButtonType> result = alert.showAndWait();
    }
}
