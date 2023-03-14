package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.EnvioACasaServiceImp;
import com.example.PeregrinosFX.service.impl.ParadaServiceImp;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class VerEnviosController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private EnvioACasaServiceImp envioACasaService;

    @Autowired
    private ParadaServiceImp paradaService;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private Button cancelarBTN;

    @FXML
    private Button aceptarBTN;

    @FXML
    private ListView enviosList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadParadaCB();
    }

    @FXML
    private void aceptarAction(ActionEvent event) throws IOException {
        enviosList.getItems().clear();
        loadEnviosList();
    }

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }


    private void loadParadaCB() {
        if (rol == 2) {
            paradaCB.setValue(RegistroController.usuarioActual.getParada().getNombre());
            paradaCB.setDisable(false);
        }
        if (rol == 3) {
            ArrayList<Parada> paradas = new ArrayList<>();
            paradas = (ArrayList<Parada>) paradaService.findAll();

            for (Parada p : paradas) {
                paradaCB.getItems().add(p.getNombre());
            }
        }
    }

    private void loadEnviosList() {
        if (rol == 2) {
            enviosList.getItems().addAll(envioACasaService.findByParada(RegistroController.usuarioActual.getParada().getIdParada()));
        }
        if (rol == 3) {
            Parada parada = paradaService.findByNombre(paradaCB.getValue().toString());
            enviosList.getItems().addAll(
                    envioACasaService.findByParada(parada.getIdParada()));
        }

    }

}
