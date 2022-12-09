package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuAdminParadaController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private Button informeparadaBTN;

    @FXML
    private Button alojarseBTN;

    @FXML
    private Button cerrarsesionBTN;

    @FXML
    private void abrirAlojarse(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.ALOJARSE);
    }

    @FXML
    private void abrirInformeParadas(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.DATOSPARADA);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
