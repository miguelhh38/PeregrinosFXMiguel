package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.User;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.UserServiceImpl;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserServiceImpl userService;

    @FXML
    private Label usuarioLBL;

    @FXML
    private Label contrasenaLBL;

    @FXML
    private TextField usuarioTF;

    @FXML
    private TextField contrasenaTF;

    @FXML
    private Button aceptarBTN;

    @FXML
    private Button cancelarBTN;

    //variable global tipo user
    public static Long rol;

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public Label getUsuarioLBL() {
        return usuarioLBL;
    }

    public void setUsuarioLBL(Label usuarioLBL) {
        this.usuarioLBL = usuarioLBL;
    }

    public Label getContrasenaLBL() {
        return contrasenaLBL;
    }

    public void setContrasenaLBL(Label contrasenaLBL) {
        this.contrasenaLBL = contrasenaLBL;
    }

    public String getUsuarioTF() {
        return usuarioTF.getText();
    }

    public void setUsuarioTF(TextField usuarioTF) {
        this.usuarioTF = usuarioTF;
    }

    public String getContrasenaTF() {
        return contrasenaTF.getText();
    }

    public void setContrasenaTF(TextField contrasenaTF) {
        this.contrasenaTF = contrasenaTF;
    }

    public Button getAceptarBTN() {
        return aceptarBTN;
    }

    public void setAceptarBTN(Button aceptarBTN) {
        this.aceptarBTN = aceptarBTN;
    }

    public Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (userService.verificarcredenciales(getUsuarioTF(), getContrasenaTF())) {
            User u = userService.findByUsuario(getUsuarioTF());
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
            Long idPerfil =u.getPerfil().getIdPerfil();
            if (idPerfil == 1) {
                rol = Long.valueOf(1);
                stageManager.switchScene(FxmlView.MENUPEREGRINO);
            } else if (idPerfil == 2) {
                rol = Long.valueOf(2);
                stageManager.switchScene(FxmlView.MENUADMINPARADA);
            } else if (idPerfil == 3) {
                rol = Long.valueOf(3);
                stageManager.switchScene(FxmlView.MENUADMINGENERAL);
            }
        } else {
            usuarioLBL.setText("LOGIN FAILED");
            usuarioLBL.setTextFill(Paint.valueOf("#FF0000"));
        }
    }
    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
