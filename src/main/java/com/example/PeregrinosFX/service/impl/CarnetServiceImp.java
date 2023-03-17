package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Carnet;
import com.example.PeregrinosFX.bean.CarnetBackup;
import com.example.PeregrinosFX.repository.CarnetRepository;
import com.example.PeregrinosFX.repository.MongoRepo;
import com.example.PeregrinosFX.service.CarnetService;
import com.example.PeregrinosFX.service.PeregrinoService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class CarnetServiceImp implements CarnetService {

    @Autowired
    private CarnetRepository carnetRepository;

    @Autowired
    private MongoRepo mongoRepository;

    @Autowired
    private PeregrinoService peregrinoService;

    @Override
    public Carnet save(Carnet entity) {
        return null;
    }

    @Override
    public Carnet update(Carnet entity) {
        return null;
    }

    @Override
    public void delete(Carnet entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Carnet> entities) {

    }

    @Override
    public Carnet find(Long id) {
        return null;
    }

    @Override
    public List<Carnet> findAll() {
        return carnetRepository.findAll();
    }

    @Override
    public Carnet addCarnet(Carnet carnet) {
        return carnetRepository.save(carnet);
    }

    public void backup(){
        ArrayList<Carnet> carnets  = (ArrayList<Carnet>) findAll();
        ArrayList<String> carnetsBackup = new ArrayList<String>();
        for(Carnet c : carnets){
            carnetsBackup.add(c.toString());
        }
        String fileName = "backupcarnets " + LocalDate.now();
        CarnetBackup carnetBackup = new CarnetBackup();
        carnetBackup.setFile(fileName);
        carnetBackup.setCarnets(carnetsBackup);
        mongoRepository.save(carnetBackup);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BACKUP COMPLETO");
        alert.setContentText("Copia de seguridad guardada exitosamente");
        alert.show();
    }


}
