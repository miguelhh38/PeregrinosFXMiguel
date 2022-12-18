package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.bean.Carnet;
import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.ParadaService;
import com.example.PeregrinosFX.service.PeregrinoService;
import com.example.PeregrinosFX.service.impl.CarnetServiceImp;
import com.example.PeregrinosFX.service.impl.EstanciaServiceImp;
import com.example.PeregrinosFX.service.impl.ParadaServiceImp;
import com.example.PeregrinosFX.service.impl.PeregrinoServiceImp;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class AlojarseController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ParadaServiceImp paradaService;

    @Autowired
    private EstanciaServiceImp estanciaService;

    @FXML
    private Label paradaLBL;

    @FXML
    private Label peregrinoLBL;

    @FXML
    private Label estanciaLBL;

    @FXML
    private Label vipLBL;

    @FXML
    private CheckBox estanciaCheck;

    @FXML
    private ComboBox paradaCB;

    @FXML
    private ComboBox peregrinoCB;

    @FXML
    private CheckBox vipCB;

    @FXML
    private Button cancelarBTN;

    @FXML
    private Button aceptarBTN;

    @Autowired
    private CarnetServiceImp carnetService;

    @Autowired
    private PeregrinoServiceImp peregrinoService;

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public Label getParadaLBL() {
        return paradaLBL;
    }

    public void setParadaLBL(Label paradaLBL) {
        this.paradaLBL = paradaLBL;
    }

    public Label getPeregrinoLBL() {
        return peregrinoLBL;
    }

    public void setPeregrinoLBL(Label peregrinoLBL) {
        this.peregrinoLBL = peregrinoLBL;
    }

    public Label getEstanciaLBL() {
        return estanciaLBL;
    }

    public void setEstanciaLBL(Label estanciaLBL) {
        this.estanciaLBL = estanciaLBL;
    }

    public Label getVipLBL() {
        return vipLBL;
    }

    public void setVipLBL(Label vipLBL) {
        this.vipLBL = vipLBL;
    }

    public CheckBox getEstanciaCheck() {
        return estanciaCheck;
    }

    public void setEstanciaCheck(CheckBox estanciaCheck) {
        this.estanciaCheck = estanciaCheck;
    }

    public ComboBox getParadaCB() {
        return paradaCB;
    }

    public void setParadaCB(ComboBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public ComboBox getPeregrinoCB() {
        return peregrinoCB;
    }

    public void setPeregrinoCB(ComboBox peregrinoCB) {
        this.peregrinoCB = peregrinoCB;
    }

    public Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }

    @FXML
    private void estanciaClick(ActionEvent event) throws IOException{
        if(estanciaCheck.isSelected()){
            vipLBL.setTextFill(Paint.valueOf("45322e"));
            vipCB.setDisable(false);
        }
        else{
            vipLBL.setTextFill(Paint.valueOf("45322e70"));
            vipCB.setDisable(true);
        }

    }
    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        if(rol == 2) {
            stageManager.switchScene(FxmlView.MENUADMINPARADA);
        }
        if(rol == 3) {
            stageManager.switchScene(FxmlView.MENUADMINGENERAL);
        }
    }

    @FXML
    private void alojarseAction(ActionEvent event) throws IOException {
        if (estanciaCheck.isSelected()) {
            if (rol == 2) {
                Peregrino peregrino = (Peregrino) peregrinoCB.getValue();
                List<Parada> paradasP = peregrino.getParadas();
                paradasP.add(RegistroController.usuarioActual.getParada());
                peregrino.setParadas(paradasP);
                peregrino.getCarnet().setDistancia(peregrino.getCarnet().getDistancia() + 100);
                carnetService.addCarnet(peregrino.getCarnet());
                peregrinoService.addPeregrino(peregrino);

                //creacion estancia
                Estancia estancia = new Estancia();
                estancia.setFecha(LocalDate.now());
                estancia.setParada(RegistroController.usuarioActual.getParada());
                estancia.setPeregrino(peregrino);
                if (vipCB.isSelected()) {
                    estancia.setVip(true);
                } else {
                    estancia.setVip(false);
                }
                peregrinoService.addPeregrino(peregrino);
                estanciaService.addEstancia(estancia);
            }
            if (rol == 3) {
                Peregrino peregrino = (Peregrino) peregrinoCB.getValue();
                List<Parada> paradasP = peregrino.getParadas();
                Parada parada = paradaService.findByNombre(paradaCB.getValue().toString());
                paradasP.add(parada);
                peregrino.setParadas(paradasP);
                peregrino.getCarnet().setDistancia(peregrino.getCarnet().getDistancia() + 100);
                carnetService.addCarnet(peregrino.getCarnet());

                //creacion estancia
                Estancia estancia = new Estancia();
                estancia.setFecha(LocalDate.now());
                estancia.setParada(parada);
                estancia.setPeregrino(peregrino);
                if (vipCB.isSelected()) {
                    estancia.setVip(true);
                } else {
                    estancia.setVip(false);
                }
                peregrinoService.addPeregrino(peregrino);
                estanciaService.addEstancia(estancia);
            }
        } else {
            if (rol == 2) {
                Peregrino peregrino = (Peregrino) peregrinoCB.getValue();
                List<Parada> paradasP = peregrino.getParadas();
                paradasP.add(RegistroController.usuarioActual.getParada());
                peregrino.setParadas(paradasP);
                peregrinoService.addPeregrino(peregrino);
                peregrino.getCarnet().setDistancia(peregrino.getCarnet().getDistancia() + 100);
                carnetService.addCarnet(peregrino.getCarnet());
            }
            if (rol == 3) {
                Peregrino peregrino = (Peregrino) peregrinoCB.getValue();
                List<Parada> paradasP = peregrino.getParadas();
                paradasP.add(paradaService.findByNombre(paradaCB.getValue().toString()));
                peregrino.setParadas(paradasP);
                peregrinoService.addPeregrino(peregrino);
                peregrino.getCarnet().setDistancia(peregrino.getCarnet().getDistancia() + 100);
                carnetService.addCarnet(peregrino.getCarnet());

            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (rol == 2) {
            paradaCB.setValue(paradaService.findByNombre(RegistroController.usuarioActual.getParada().getNombre()).getNombre());
            paradaCB.setDisable(true);
            loadPeregrinoCB();
        }
        if (rol == 3) {
            loadParadaCB();
            loadPeregrinoCB();
        }
    }

    private void loadParadaCB() {
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();

        for (Parada p : paradas) {
            paradaCB.getItems().add(p.getNombre());
        }
    }

    private void loadPeregrinoCB() {
        ObservableList<Peregrino> peregrinos = FXCollections.observableList(peregrinoService.findAll());
        peregrinoCB.setItems(peregrinos);
    }
}
