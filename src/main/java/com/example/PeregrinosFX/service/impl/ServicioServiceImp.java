package com.example.PeregrinosFX.service.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.bean.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioServiceImp {

    public static final ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "servicios.db4o");
    public void verdatosDB40() {
        ObjectSet<Servicio> result = db.query(Servicio.class);

        while (result.hasNext()) {
            Servicio servicio = result.next();
            System.out.println(servicio.toString());
        }

    }

    public ObservableList<Servicio> findServicios() {
        ObjectSet<Servicio> result = db.queryByExample(Servicio.class);
        ObservableList<Servicio> servicios = FXCollections.observableArrayList(result);
        return servicios;

    }

    public void guardarNuevoServicio(String nombre, double precio, ArrayList<Integer> paradas) {
        List<Servicio> result = db.query(new Predicate<Servicio>() {
            @Override
            public boolean match(Servicio servicio) {
                return servicio.getNombre().equalsIgnoreCase(nombre);
            }
        });
        if (result.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL GUARDAR SERVICIO");
            alert.setContentText("El servicio que intenta guardar ya existe en la base de datos");
            alert.show();

        } else {
            Servicio nuevoServicio = new Servicio(nombre, precio, paradas);
            db.store(nuevoServicio);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DATOS CORRECTOS");
            alert.setContentText("El servicio ha sido creado correctamente");
            alert.show();

        }

    }

    public void editarServicio(Servicio servicio, String nombre, double precio, ArrayList<Integer> paradas) {
        try {
            ObjectSet<Servicio> result = db.queryByExample(servicio);
            Servicio servicioUpdate = result.next();
            servicioUpdate.setNombre(nombre);
            servicioUpdate.setPrecio(precio);
            servicioUpdate.setParadas(paradas);
            db.store(servicioUpdate);
            db.commit();

        } finally {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DATOS CORRECTOS");
            alert.setContentText("El servicio ha sido actualizado correctamente");
            alert.show();
        }

    }

    public ObservableList<Servicio> findByParada(Parada parada) {
        ObjectSet<Servicio> result = db.queryByExample(Servicio.class);
        ObservableList<Servicio> servicios = FXCollections.observableArrayList(result);

        ObservableList<Servicio> serviciosParada;
        serviciosParada = FXCollections.observableArrayList();

        for (Servicio servicio: servicios) {
            if (servicio.getParadas().contains((int) parada.getIdParada())) {
                serviciosParada.add(servicio);
            }
        }
        return serviciosParada;
    }



    public double sumaPrecio(ObservableList<Servicio> servicios) {
        double suma = 0;
        for (Servicio servicio: servicios) {
            suma += servicio.getPrecio();
        }
        return suma;
    }
}
