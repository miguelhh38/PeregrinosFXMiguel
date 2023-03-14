package com.example.PeregrinosFX.service.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.PeregrinosFX.bean.ConjuntoContratado;
import com.example.PeregrinosFX.bean.Servicio;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.PeregrinosFX.service.impl.ServicioServiceImp.db;


@Service
public class ConjuntoServiceImp {

    public void verdatosDB40Conjunto() {
        ObjectSet<ConjuntoContratado> result = db.query(ConjuntoContratado.class);
        ArrayList<ConjuntoContratado> ccs = new ArrayList<ConjuntoContratado>();
        while (result.hasNext()) {
            ConjuntoContratado conj = result.next();
            ccs.add(conj);
        }

        for(ConjuntoContratado cc : ccs){
            System.out.println(cc.toString());
        }

    }

    public void guardarConjunto(ConjuntoContratado cc) {

            db.store(cc);
            verdatosDB40Conjunto();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DATOS CORRECTOS");
            alert.setContentText("El conjunto ha sido creado correctamente");
            alert.show();


        }
}

