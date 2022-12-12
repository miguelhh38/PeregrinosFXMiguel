package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.PeregrinoServiceImp;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class ExportarCarnetController implements Initializable{

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private PeregrinoServiceImp peregrinoService;

    @FXML
    private Button expcarnetBTN;

    @FXML
    private Button cancelarBTN;

    @FXML
    private ComboBox peregrinoCB;

    @FXML
    private ScrollPane xmlexport;

    @FXML
    private void exportarCAction(ActionEvent event) throws IOException {
        //CODIGO MUESTRA XML EXPORTADO
        if (rol == 1) {
            peregrinoService.exportarCarnetXML(RegistroController.usuarioActual.getPeregrino());

        }
        if (rol == 3) {
            peregrinoService.exportarCarnetXML((Peregrino) peregrinoCB.getValue());

        }

    }

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        if(rol == 1) {
            stageManager.switchScene(FxmlView.MENUPEREGRINO);
        }
        if(rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }
    @FXML
    private void abrirRegistro(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.REGISTRO);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPeregrinoCB();
        if (rol == 1) {
            peregrinoCB.setEditable(false);
            peregrinoCB.setDisable(true);
            peregrinoCB.setValue(RegistroController.usuarioActual.getPeregrino().peregrinoData());
        }
        if (rol == 2) {
            peregrinoCB.setDisable(false);
        }

    }

    private void loadPeregrinoCB() {
        ObservableList<Peregrino> peregrinos = FXCollections.observableList(peregrinoService.findAll());
        for (Peregrino p: peregrinos) {
            peregrinoCB.getItems().add(p.peregrinoData());
        }
    }
}
