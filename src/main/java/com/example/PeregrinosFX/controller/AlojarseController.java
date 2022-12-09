package com.example.PeregrinosFX.controller;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.view.FxmlView;
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
import java.util.ResourceBundle;

import static com.example.PeregrinosFX.controller.LoginController.rol;

@Controller
public class AlojarseController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

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
    private ToggleGroup vip;

    @FXML
    private RadioButton siRB;

    @FXML
    private RadioButton noRB;

    @FXML
    private Button alojarseBTN;

    @FXML
    private Button cancelarBTN;

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

    public ToggleGroup getVip() {
        return vip;
    }

    public void setVip(ToggleGroup vip) {
        this.vip = vip;
    }

    public RadioButton getSiRB() {
        return siRB;
    }

    public void setSiRB(RadioButton siRB) {
        this.siRB = siRB;
    }

    public RadioButton getNoRB() {
        return noRB;
    }

    public void setNoRB(RadioButton noRB) {
        this.noRB = noRB;
    }

    public Button getAlojarseBTN() {
        return alojarseBTN;
    }

    public void setAlojarseBTN(Button alojarseBTN) {
        this.alojarseBTN = alojarseBTN;
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
            siRB.setTextFill(Paint.valueOf("45322e"));
            noRB.setTextFill(Paint.valueOf("45322e"));
        }
        else{
            vipLBL.setTextFill(Paint.valueOf("45322e70"));
            siRB.setTextFill(Paint.valueOf("45322e70"));
            noRB.setTextFill(Paint.valueOf("45322e70"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
