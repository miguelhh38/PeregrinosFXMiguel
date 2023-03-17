package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.repository.ExitDB;
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
public class verCarnetsController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ParadaServiceImp paradaService;


    @FXML
    private Label paradaTEXT;

    @FXML
    private Button cancelarBTN;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private ListView listviewCarnets;

    @FXML
    private Button detalles;

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
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
            paradaCB.setValue(paradaService.findByNombre(RegistroController.usuarioActual.getParada().getNombre()).getNombre());
            paradaCB.setDisable(true);
        }
        if (rol == 3) {
            loadParadaCB();
        }
        loadlistviewCarnets();
    }

    private void loadParadaCB() {
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();
        for (Parada p : paradas) {
            paradaCB.getItems().add(p.getNombre());
        }

        if (rol == 3) {
            ExitDB exitDB = new ExitDB();
            paradaCB.valueProperty().addListener((observable, oldValue, newValue) -> {
                listviewCarnets.getItems().clear();
                exitDB.verDatosCol(paradaService.findByNombre(paradaCB.getValue().toString())
                        ,listviewCarnets);

            });
        }


    }

    private void loadlistviewCarnets() {
        ExitDB exitDB = new ExitDB();
        if (rol == 2) {
            exitDB.verDatosCol(paradaService.findByNombre(paradaCB.getValue().toString()), listviewCarnets);
        }
    }

    @FXML
    private void verDetallesAction(ActionEvent event) throws IOException {
        Parada parada = new Parada();

        if (rol == 2) {
            parada = RegistroController.usuarioActual.getParada();
        }
        if (rol == 3) {
            parada = paradaService.findByNombre(paradaCB.getValue().toString());
        }

        ExitDB exitDB = new ExitDB();


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DETALLES");
            alert.setHeaderText(null);
            alert.setContentText(exitDB.buscarPorParadaNombre(parada, listviewCarnets.getSelectionModel().getSelectedItem().toString()));
            alert.show();



    }


}
