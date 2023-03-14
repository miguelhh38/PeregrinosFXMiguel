package com.example.PeregrinosFX.controller;

import com.db4o.ObjectSet;
import com.example.PeregrinosFX.bean.*;
import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.service.ParadaService;
import com.example.PeregrinosFX.service.PeregrinoService;
import com.example.PeregrinosFX.service.impl.*;
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

    public static long paradaSel;
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ParadaServiceImp paradaService;

    @Autowired
    private ConjuntoServiceImp conjuntoServiceImp;

    @Autowired
    private EstanciaServiceImp estanciaService;

    @Autowired
    private ServicioServiceImp servicioServiceImp;

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

    @FXML
    private ListView serviciosLIST;

    @FXML
    private ComboBox metodoCB;

    @FXML
    private Label precioLABEL;

    @FXML
    private CheckBox serviciosCheck;

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
    private Label servicioASK;


    @FXML
    private void estanciaClick(ActionEvent event) throws IOException{
        if(estanciaCheck.isSelected()){
            vipLBL.setTextFill(Paint.valueOf("45322e"));
            vipCB.setDisable(false);
            servicioASK.setTextFill(Paint.valueOf("45322e"));
            serviciosCheck.setDisable(false);
        }
        else{
            vipLBL.setTextFill(Paint.valueOf("45322e70"));
            vipCB.setDisable(true);
            vipLBL.setTextFill(Paint.valueOf("45322e70"));
            servicioASK.setTextFill(Paint.valueOf("45322e70"));
            serviciosCheck.setDisable(true);
            serviciosLIST.setDisable(true);
        }

    }

    @FXML
    private Label metodoLABEL;

    @FXML
    private Label totalLABEL;

    @FXML
    private Label extraLABEL;

    @FXML
    private TextArea extraTEXT;


    @FXML
    private void serviciosClick(ActionEvent event) throws IOException {
        if (serviciosCheck.isSelected()) {
            metodoLABEL.setTextFill(Paint.valueOf("45322e"));
            totalLABEL.setTextFill(Paint.valueOf("45322e"));
            metodoCB.setDisable(false);
            serviciosLIST.setDisable(false);
            extraLABEL.setTextFill(Paint.valueOf("45322e"));
            extraTEXT.setDisable(false);


        } else {
            metodoLABEL.setTextFill(Paint.valueOf("45322e70"));
            extraLABEL.setTextFill(Paint.valueOf("45322e70"));
            totalLABEL.setTextFill(Paint.valueOf("45322e70"));
            metodoCB.setDisable(true);
            extraTEXT.setDisable(true);
            serviciosLIST.setDisable(true);
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

                //creacion conjunto contratado
                if (serviciosCheck.isSelected()) {
                    String errores = "";
                    if (serviciosLIST.getSelectionModel().isEmpty()) {
                        errores += "Debe de seleccionar algun servicio. \n";
                    }
                    if (metodoCB.getValue() == null) {
                        errores += "Debe de seleccionar un metodo de pago .\n";
                    }
                    if (errores.isEmpty()) {
                        peregrinoService.addPeregrino(peregrino);
                        estanciaService.addEstancia(estancia);

                        String metodo = metodoCB.getValue().toString();
                        char metodo1 = 'E';
                        if (metodo.equalsIgnoreCase("Efectivo")) {
                            metodo1 = 'E';
                        }
                        if (metodo.equalsIgnoreCase("Tarjeta")) {
                            metodo1 = 'T';
                        }
                        if (metodo.equalsIgnoreCase("Bizum")) {
                            metodo1 = 'B';
                        }

                        ArrayList<Servicio> serviciosSelected = new ArrayList<>();
                        for (int i=0; i<serviciosLIST.getSelectionModel().getSelectedItems().size(); i++) {
                            serviciosSelected.add((Servicio) serviciosLIST.getSelectionModel().getSelectedItems().get(i));
                        }

                        ConjuntoContratado cc = new ConjuntoContratado(estancia.getIdEstancia(),
                                servicioServiceImp.sumaPrecio(serviciosLIST.getSelectionModel().getSelectedItems()),
                                metodo1, extraTEXT.getText(), serviciosSelected);
                        conjuntoServiceImp.guardarConjunto(cc);

                        for (Servicio s1: cc.getServicios()) {
                            if (s1.getNombre().equals("Envio a casa")) {
                                stageManager.switchScene(FxmlView.ENVIOCASA);

                            }
                        }

                    } else {
                        errorCreacionConjunto(errores);
                    }
                }

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


                //creacion conjunto contratado
                if (serviciosCheck.isSelected()) {
                    String errores = "";
                    if (serviciosLIST.getSelectionModel().isEmpty()) {
                        errores += "Debe de seleccionar algun servicio. \n";
                    }
                    if (metodoCB.getValue() == null) {
                        errores += "Debe de seleccionar un metodo de pago .\n";
                    }
                    if (errores.isEmpty()) {
                        peregrinoService.addPeregrino(peregrino);
                        estanciaService.addEstancia(estancia);

                        String metodo = metodoCB.getValue().toString();
                        char metodo1 = 'E';
                        if (metodo.equalsIgnoreCase("Efectivo")) {
                            metodo1 = 'E';
                        }
                        if (metodo.equalsIgnoreCase("Tarjeta")) {
                            metodo1 = 'T';
                        }
                        if (metodo.equalsIgnoreCase("Bizum")) {
                            metodo1 = 'B';
                        }

                        ArrayList<Servicio> serviciosSelected = new ArrayList<>();
                        for (int i=0; i<serviciosLIST.getSelectionModel().getSelectedItems().size(); i++) {
                            serviciosSelected.add((Servicio) serviciosLIST.getSelectionModel().getSelectedItems().get(i));
                        }
                        ConjuntoContratado cc = new ConjuntoContratado(estancia.getIdEstancia(),
                                servicioServiceImp.sumaPrecio(serviciosLIST.getSelectionModel().getSelectedItems()),
                                metodo1, extraTEXT.getText(), serviciosSelected);
                        conjuntoServiceImp.guardarConjunto(cc);

                        for (Servicio s1: cc.getServicios()) {
                            if (s1.getNombre().equals("Envio a casa")) {
                                paradaSel = parada.getIdParada();
                                stageManager.switchScene(FxmlView.ENVIOCASA);
                            }
                        }

                    } else {
                        errorCreacionConjunto(errores);
                    }
                }
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
        serviciosCheck.setDisable(true);
        serviciosLIST.setDisable(true);
        extraTEXT.setDisable(true);
        if (rol == 2) {
            paradaCB.setValue(paradaService.findByNombre(RegistroController.usuarioActual.getParada().getNombre()).getNombre());
            paradaCB.setDisable(true);
            loadPeregrinoCB();
            serviciosLIST.getItems().setAll(servicioServiceImp.findByParada(RegistroController.usuarioActual.getParada()));
        }
        if (rol == 3) {
            loadParadaCB();
            loadPeregrinoCB();
        }

        loadServiciosLIST();
        loadMetodosCB();
    }

    private void loadParadaCB() {
        ArrayList<Parada> paradas = new ArrayList<>();
        paradas = (ArrayList<Parada>) paradaService.findAll();
        for (Parada p : paradas) {
            paradaCB.getItems().add(p.getNombre());
        }

        paradaCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            serviciosLIST.getItems().clear();
            serviciosLIST.getItems().addAll(servicioServiceImp.findByParada(
                    paradaService.findByNombre(newValue.toString())));

        });
    }

    private void loadPeregrinoCB() {
        ObservableList<Peregrino> peregrinos = FXCollections.observableList(peregrinoService.findAll());
        peregrinoCB.setItems(peregrinos);
    }

    private void loadServiciosLIST() {
        serviciosLIST.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        serviciosLIST.setOnMouseClicked((event -> {
            precioLABEL.setText(String.valueOf(
                    servicioServiceImp.sumaPrecio(
                            serviciosLIST.getSelectionModel().getSelectedItems())) + " €");
        }));

    }

    private void loadMetodosCB() {
        metodoCB.getItems().addAll(
                "Efectivo",
                "Tarjeta",
                "Bizum"
        );
    }

    private void errorCreacionConjunto(String errores) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR AL CONTRATAR SERVICIOS");
        alert.setContentText(errores);
        alert.show();
    }
}
