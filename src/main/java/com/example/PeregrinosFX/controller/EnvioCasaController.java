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
        envioACasaService.realizarEnvio(dirTEXT, localidadTEXT, sizeTEXT, pesoTEXT, urgenteCHECK);
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }
}
