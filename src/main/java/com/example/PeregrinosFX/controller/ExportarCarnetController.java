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

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class ExportarCarnetController implements Initializable{

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private Button expcarnetBTN;

    @FXML
    private Button cancelarBTN;

    @FXML
    private void exportarCAction(ActionEvent event) throws IOException {
        //CODIGO MUESTRA XML EXPORTADO
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

    }
}
