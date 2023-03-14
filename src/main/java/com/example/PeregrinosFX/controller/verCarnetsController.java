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
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
        stageManager.switchScene(FxmlView.MENUADMINPARADA);
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

    }

    private void loadlistviewCarnets() {
        ExitDB exitDB = new ExitDB();
        if (rol==2) {
            exitDB.verDatosCol(paradaService.findByNombre(paradaCB.getValue().toString()), listviewCarnets);
        }
        if (rol == 3) {

        }
    }

    @FXML
    private void verDetallesAction(ActionEvent event) throws IOException {

        URL resourceURL = getClass().getResource(System.getProperty("user.dir") + "/export/" +
                listviewCarnets.getSelectionModel().getSelectedItem().toString());

        try (InputStream inputStream = resourceURL.openStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // Hacer algo con el documento XML cargado, como mostrarlo en un TextArea
            // ...
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR DE REGISTRO");
            alert.setHeaderText(null);
            alert.setContentText(document.getTextContent().toString());
            Optional<ButtonType> result = alert.showAndWait();
            alert.show();

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }


}
