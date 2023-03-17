package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.repository.CarnetRepository;
import com.example.PeregrinosFX.service.impl.CarnetServiceImp;
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
public class MenuAdminGeneralController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private CarnetServiceImp carnetServiceImp;

    @FXML
    private Button informeparadaBTN;

    @FXML
    private Button exportarcarnetBTN;

    @FXML
    private Button alojarseBTN;

    @FXML
    private Button cerrarsesionBTN;

    @FXML
    private Button serviciosBTN;

    @FXML
    private Button enviosBUTTON;

    @FXML
    private Button backupCarnets;

    @FXML
    private void abrirAlojarse(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.ALOJARSE);
    }

    @FXML
    private void abrirExportarCarnet(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.EXPORTARCARNET);
    }

    @FXML
    private void abrirInformeParadas(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.DATOSPARADA);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }

    @FXML
    private void abrirServciosMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUSERVCIOS);
    }

    @FXML
    private void enviosAction(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.VERENVIOS);
    }

    @FXML
    private void verCarnets(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.VERCARNETS);
    }

    @FXML
    private void backupCarnetsAction(ActionEvent event) throws IOException {
        carnetServiceImp.backup();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
