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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuServiciosController implements Initializable {


    @Lazy
    @Autowired
    private StageManager stageManager;
    @FXML
    private Button nuevoServicioBTN;

    @FXML
    private Button editarservicioBTN;

    @FXML
    private Button cerrarsesionBTN;

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUADMINGENERAL);
    }

    @FXML
    private void abrirNuevoServicio(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.NUEVOSERVICIO);
    }

    @FXML
    private void abrirEditarServicio(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.EDITARSERVICIO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
