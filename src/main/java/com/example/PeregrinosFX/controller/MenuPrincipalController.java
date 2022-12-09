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
public class MenuPrincipalController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;
    @FXML
    private Button iniciarsesionBTN;


    @FXML
    private Button registrarseBTN;

    @FXML
    private void abrirRegistro(ActionEvent event) throws IOException{
        stageManager.switchScene(FxmlView.REGISTRO);
    }

    @FXML
    private void abrirLogin(ActionEvent event) throws IOException{
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
