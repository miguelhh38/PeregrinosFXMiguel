package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Servicio;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.impl.ParadaServiceImp;
import com.example.PeregrinosFX.service.impl.ServicioServiceImp;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class NuevoServicioController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ParadaServiceImp paradaService;

    @FXML
    private Button cancelarBTN;

    @FXML
    private Button confirmarBTN;

    @FXML
    private ListView paradasLIST;

    @FXML
    private TextField nombreTFiel;

    @FXML
    private TextField precioTFIEL;


    @Autowired
    private ServicioServiceImp servicioServiceImp;

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        String errores = "";
        if (paradasLIST.getSelectionModel().isEmpty()) {
            errores += "Debe de seleccionar alguna parada.\n";
        }
        if (nombreTFiel.getText().length()<=0) {
            errores += "Debe ingresar el nombre del servicio.\n";
        }
        if (precioTFIEL.getText().length()<=0) {
            errores += "Debe ingresar el precio del servicio.\n";
        }
        if (Utils.leerDouble(precioTFIEL.getText())) {
            errores += "Formato del precio incorrecto. Introduce numero decimal (x.x).\n";
        }

        if (errores.isEmpty()) {
            servicioServiceImp.guardarNuevoServicio(nombreTFiel.getText(),
                    Double.parseDouble(precioTFIEL.getText()),
                    paradaService.guardarID(paradasLIST.getSelectionModel().getSelectedItems()));
            servicioServiceImp.verdatosDB40();


        } else {
            errorAlert(errores);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUSERVCIOS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadParadasList();
    }

    public void loadParadasList() {
        paradasLIST.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaService.findAll();
        for (Parada p: paradas) {
            paradasLIST.getItems().add(p.getNombre());
        }
    }

    private void errorAlert(String errores) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DATOS MAL INTRODUCIDOS");
        alert.setHeaderText(null);
        alert.setContentText(errores);
        Optional<ButtonType> result = alert.showAndWait();
    }


}
