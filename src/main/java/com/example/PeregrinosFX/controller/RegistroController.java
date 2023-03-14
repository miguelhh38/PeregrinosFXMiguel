package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.*;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.CarnetServiceImp;
import com.example.PeregrinosFX.service.impl.ParadaServiceImp;
import com.example.PeregrinosFX.service.impl.PeregrinoServiceImp;
import com.example.PeregrinosFX.service.impl.UserServiceImpl;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.fxml.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class RegistroController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PeregrinoServiceImp peregrinoService;

    @Autowired
    private CarnetServiceImp carnetService;

    @Autowired
    private ParadaServiceImp paradaService;

    @FXML
    private Label nombreLBL;

    @FXML
    private Label usuarioLBL;

    @FXML
    private Label contrasenaLBL;

    @FXML
    private Label confirmcontraLBL;

    @FXML
    private Label paradaLBL;

    @FXML
    private Label nacionalidadLBL;

    @FXML
    private TextField nombreTF;

    @FXML
    private TextField usuarioTF;

    @FXML
    private TextField contrasenaTF;

    @FXML
    private TextField confirmcontraTF;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private ComboBox nacionalidadCB;

    @FXML
    private Button aceptarBTN;

    @FXML
    private Button cancelarBTN;

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }

    public static User usuarioActual;

    public Label getNombreLBL() {
        return nombreLBL;
    }

    public void setNombreLBL(Label nombreLBL) {
        this.nombreLBL = nombreLBL;
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

    public Label getConfirmcontraLBL() {
        return confirmcontraLBL;
    }

    public void setConfirmcontraLBL(Label confirmcontraLBL) {
        this.confirmcontraLBL = confirmcontraLBL;
    }

    public Label getParadaLBL() {
        return paradaLBL;
    }

    public void setParadaLBL(Label paradaLBL) {
        this.paradaLBL = paradaLBL;
    }

    public Label getNacionalidadLBL() {
        return nacionalidadLBL;
    }

    public void setNacionalidadLBL(Label nacionalidadLBL) {
        this.nacionalidadLBL = nacionalidadLBL;
    }

    public TextField getNombreTF() {
        return nombreTF;
    }

    public void setNombreTF(TextField nombreTF) {
        this.nombreTF = nombreTF;
    }

    public TextField getUsuarioTF() {
        return usuarioTF;
    }

    public void setUsuarioTF(TextField usuarioTF) {
        this.usuarioTF = usuarioTF;
    }

    public TextField getContrasenaTF() {
        return contrasenaTF;
    }

    public void setContrasenaTF(TextField contrasenaTF) {
        this.contrasenaTF = contrasenaTF;
    }

    public TextField getConfirmcontraTF() {
        return confirmcontraTF;
    }

    public void setConfirmcontraTF(TextField confirmcontraTF) {
        this.confirmcontraTF = confirmcontraTF;
    }

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public ComboBox getNacionalidadCB() {
        return nacionalidadCB;
    }

    public void setNacionalidadCB(ComboBox nacionalidadCB) {
        this.nacionalidadCB = nacionalidadCB;
    }

    @FXML
    private void aceptarAction(ActionEvent event) throws IOException {
        String errores = "";
        if (nombreTF.getText().length() < 1 || nombreTF.getText().length() > 40) {
            errores += "Nombre no válido. Introduce un nombre comprendido entre 0 y 40 caracteres.\n";
            nombreLBL.setTextFill(Color.RED);
        }
        if (getUsuarioTF().getText().length() < 1 || getNombreTF().getText().length() > 40) {
            errores += "Usuario no válido. Introduce un usuario comprendido entre 0 y 40 caractetes.\n";
            usuarioLBL.setTextFill(Color.RED);
        }
        if (userService.comprobarUsuario(getUsuarioTF().getText())) {
            errores += "Usuario no válido. El nombre de usuario no está disponible.\n";
            usuarioLBL.setTextFill(Color.RED);
        }

        if (getContrasenaTF().getText().length() < 1 || getContrasenaTF().getText().length() > 40) {
            errores += "Contraseña no válida. Introduce una contraseña comprendida entre 0 y 40 caracteres.\n";
            contrasenaLBL.setTextFill(Color.RED);
        }
        if (!getConfirmcontraTF().getText().equals(getContrasenaTF().getText())) {
            errores += "Confirmación de contraseña incorrecta. Las contraseñas no coinciden.\n";
            confirmcontraLBL.setTextFill(Color.RED);
        }

        if (errores.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMACION USUARIO");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea crear el usuario " + usuarioTF.getText() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //CODIGO CREAR USUARIO BD
                usuarioActual = new User();
                Perfil perfilaux = new Perfil();
                perfilaux.setIdPerfil(1L);

                User user = new User();
                Carnet carnet = new Carnet();
                Peregrino peregrino = new Peregrino();

                user.setUsuario(usuarioTF.getText());
                user.setContrasenia(contrasenaTF.getText());
                user.setPerfil(perfilaux);
                user.setPeregrino(peregrino);


                carnet.setDistancia(0);
                carnet.setNumVips(0);
                carnet.setFechaExp(LocalDate.now());
                carnet.setParadaInicial(paradaService.findByNombre(paradaCB.getValue().toString()));
                Carnet newCarnet = carnetService.addCarnet(carnet);

                peregrino.setNombre(nombreTF.getText());
                peregrino.setNacionalidad((String) nacionalidadCB.getValue());
                peregrino.setCarnet(newCarnet);
                ArrayList<Parada> paradas = new ArrayList<>();
                paradas.add(paradaService.findByNombre(paradaCB.getValue().toString()));
                peregrino.setParadas(paradas);

                peregrinoService.addPeregrino(peregrino);
                usuarioActual = userService.addUser(user);

                //EXITDB
                peregrinoService.exportarCarnetXMLExitDB(peregrino);

                stageManager.switchScene(FxmlView.USUARIOCREADO);

            }
        } else {
            //aviso
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR DE REGISTRO");
            alert.setHeaderText(null);
            alert.setContentText(errores);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadParadaCB();
        loadNacionalidadesCB();
    }

    private void loadParadaCB() {
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();

        for (Parada p : paradas) {
            paradaCB.getItems().add(p.getNombre());
        }
    }

    private void loadNacionalidadesCB() {
        nacionalidadCB.getItems().addAll(
            "Alemania",
                "Andorra",
                "Argentina",
                "Bélgica",
                "Brasil",
                "Colombia",
                "España",
                "Francia",
                "México",
                "Reino Unido"
        );

    }
}
