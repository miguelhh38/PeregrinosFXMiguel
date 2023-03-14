package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Direccion;
import com.example.PeregrinosFX.bean.EnvioACasa;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.repository.ObjectDBRepository;
import com.example.PeregrinosFX.service.EnvioACasaService;
import com.example.PeregrinosFX.service.impl.EnvioACasaServiceImp;
import com.example.PeregrinosFX.utils.Utils;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.AlojarseController.paradaSel;
import static com.example.PeregrinosFX.controller.LoginController.rol;
import static com.example.PeregrinosFX.repository.ObjectDBRepository.em;

@Controller
public class EnvioCasaController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private EnvioACasaServiceImp envioACasaService;

    @FXML
    private Button confirmar;

    @FXML
    private TextField dirTEXT;

    @FXML
    private TextField localidadTEXT;

    @FXML
    private TextField pesoTEXT;

    @FXML
    private TextField sizeTEXT;

    @FXML
    private CheckBox urgenteCHECK;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void confirmarAction(ActionEvent event) throws IOException {
        String errores = "";
        if (dirTEXT.getText().length() < 3) {
            errores += "La dirección no es válida.\n";
        }
        if (localidadTEXT.getText().length()<2) {
            errores += "La localidad no es válida.\n";
        }
        if (!Utils.leerInt(sizeTEXT.getText())) {
            errores += "Formato del tamaño incorrecto. Introduce numero entero.\n";
        }
        if (!Utils.leerDouble(pesoTEXT.getText())) {
            errores += "Formato del peso incorrecto. Introduce numero decimal (x.x).\n";
        }

        if (errores.isEmpty()) {
            if (rol == 2) {
                Direccion direccion = new Direccion(dirTEXT.getText(), localidadTEXT.getText());


                EnvioACasa envioACasa = new EnvioACasa(Double.parseDouble(pesoTEXT.getText()),
                        Integer.parseInt(sizeTEXT.getText()), urgenteCHECK.isSelected(), direccion,
                        RegistroController.usuarioActual.getParada().getIdParada());
                envioACasaService.addEnvio(envioACasa);

            }
            if (rol == 3) {
                Direccion direccion = new Direccion(dirTEXT.getText(), localidadTEXT.getText());

                EnvioACasa envioACasa = new EnvioACasa(Double.parseDouble(pesoTEXT.getText()),
                        Integer.parseInt(sizeTEXT.getText()), urgenteCHECK.isSelected(), direccion,
                        AlojarseController.paradaSel);
                envioACasaService.addEnvio(envioACasa);
                envioGuardado();
                stageManager.switchScene(FxmlView.MENUADMINGENERAL);
            }
        } else {
            error(errores);
        }

    }

    private void envioGuardado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ENVIO GUARDADO");
        alert.setHeaderText(null);
        alert.setContentText("El envio ha sido guardado correctamente");
        Optional<ButtonType> result = alert.showAndWait();
    }

    private void error(String errores) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR AL GUARDAR ENVIO");
        alert.setHeaderText(null);
        alert.setContentText(errores);
        Optional<ButtonType> result = alert.showAndWait();
    }

}
