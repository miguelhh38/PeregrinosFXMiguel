package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.RegistroController.usuarioActual;

@Controller
public class UsuarioCreadoController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private TextField passTXT;

    @FXML
    private TextField usuarioTXT;

    @FXML
    private TextField nacionalidadTXT;

    @FXML
    private TextField paradaTXT;

    @FXML
    private Button inicioBTN;

    @FXML
    private void inicioAction(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPEREGRINO);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioTXT.setText(usuarioActual.getUsuario());
        passTXT.setText(usuarioActual.getContrasenia());
        nacionalidadTXT.setText(usuarioActual.getPeregrino().getNacionalidad());
        paradaTXT.setText(usuarioActual.getPeregrino().getCarnet().getParadaInicial().toString());

    }

    public TextField getPassField() {
        return passTXT;
    }

    public void setPassTXT(TextField passTXT) {
        this.passTXT = passTXT;
    }

    public TextField getUsuarioTXT() {
        return usuarioTXT;
    }

    public void setUsuarioTXT(TextField usuarioTXT) {
        this.usuarioTXT = usuarioTXT;
    }

    public TextField getNacionalidadTXT() {
        return nacionalidadTXT;
    }

    public void setNacionalidadTXT(TextField nacionalidadTXT) {
        this.nacionalidadTXT = nacionalidadTXT;
    }

    public TextField getParadaTXT() {
        return paradaTXT;
    }

    public void setParadaTXT(TextField paradaTXT) {
        this.paradaTXT = paradaTXT;
    }



}



