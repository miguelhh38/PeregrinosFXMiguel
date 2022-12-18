package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.EstanciaServiceImp;
import com.example.PeregrinosFX.service.impl.ParadaServiceImp;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class DatosParadaController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;


    @FXML
    private ComboBox paradaCB;

    @FXML
    private Label paradaLBL;

    @FXML
    private Label fechainicialLBL;

    @FXML
    private Label fechafinalLBL;

    @FXML
    private Label estanciasLBL;

    @FXML
    private DatePicker fechainicialDATE;

    @FXML
    private DatePicker fechafinalDATE;

    @Autowired
    private ParadaServiceImp paradaService;

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    @FXML
    private Button inicioBTN;

    @FXML
    private Button buscarBTN;

    @FXML
    private TextArea textArea;

    @Autowired
    private EstanciaServiceImp estanciaService;

    @FXML
    private void buscarAction(ActionEvent event) throws IOException {
        if (rol == 2) {
            textArea.deleteText(0, textArea.getText().length());
            textArea.appendText(estanciaService.mostrarEstancias(estanciaService.filtrarEstancias(RegistroController.usuarioActual.getParada(), fechainicialDATE.getValue(), fechafinalDATE.getValue())));
        }
        if (rol == 3) {
            textArea.deleteText(0, textArea.getText().length());
            textArea.appendText(estanciaService.mostrarEstancias(estanciaService.filtrarEstancias(paradaService.findByNombre(paradaCB.getValue().toString()), fechainicialDATE.getValue(), fechafinalDATE.getValue())));

        }
    }

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        if (rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if (rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (rol == 2) {
            paradaCB.setValue(RegistroController.usuarioActual.getParada().getNombre());
            paradaCB.setDisable(false);
        }
        if (rol == 3) {
            loadParadaCB();
        }
    }

    private void loadParadaCB() {
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();

        for (Parada p : paradas) {
            paradaCB.getItems().add(p.getNombre());
        }
    }
}
