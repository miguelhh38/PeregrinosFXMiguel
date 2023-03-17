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
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class EditarServicioController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ParadaServiceImp paradaService;

    @Autowired
    private ServicioServiceImp service;

    @FXML
    private ComboBox<Servicio> servicioCB;


    @FXML
    private Label paradasLABEL;

    @FXML
    private Label precioLABEL;

    @FXML
    private TextField nombreTEXT;

    @FXML
    private TextField precioTEXT;

    @FXML
    private ListView paradasLIST;

    @FXML
    private Button cancelarBTN;

    @FXML
    private Button guardarBTN;

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.MENUSERVCIOS);
    }


    @FXML
    private void guardarCambios(ActionEvent event) throws IOException {
        service.editarServicio(paradasLIST, nombreTEXT, precioTEXT, servicioCB, paradaService, stageManager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadParadasList();
        loadServiciosCB();

    }

    public void loadServiciosCB() {
        servicioCB.getItems().addAll(service.findServicios());

        servicioCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            precioLABEL.setText(String.valueOf(servicioCB.getValue().getPrecio()) + " €");
            paradasLABEL.setText(paradaService.buscarParadas(servicioCB.getValue().getParadas()));

                });
    }
    public void loadParadasList() {
        paradasLIST.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaService.findAll();
        for (Parada p: paradas) {
            paradasLIST.getItems().add(p.getNombre());
        }
    }



}
