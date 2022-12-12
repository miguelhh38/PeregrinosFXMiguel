package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.Estancia;
import com.example.PeregrinosFX.bean.Peregrino;
import com.example.PeregrinosFX.controller.RegistroController;
import com.example.PeregrinosFX.repository.PeregrinoRepository;
import com.example.PeregrinosFX.service.PeregrinoService;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PeregrinoServiceImp implements PeregrinoService {

    @Autowired
    private PeregrinoRepository peregrinoRepository;

    @Override
    public Peregrino save(Peregrino entity) {
        return null;
    }

    @Override
    public Peregrino update(Peregrino entity) {
        return null;
    }

    @Override
    public void delete(Peregrino entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<Peregrino> entities) {

    }

    @Override
    public Peregrino find(Long id) {
        return peregrinoRepository.findById(id).get();
    }

    @Override
    public List<Peregrino> findAll() {
        return peregrinoRepository.findAll();
    }

    @Override
    public Peregrino addPeregrino(Peregrino peregrino) {
        return peregrinoRepository.save(peregrino);
    }

    @Override
    public void exportarCarnetXML(Peregrino p) {
        String path = p.getNombre() + "_peregrino.xml";
        try {
            DocumentBuilderFactory fabricaConstructorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructorDocumento = fabricaConstructorDocumento.newDocumentBuilder();
            DOMImplementation implementation = constructorDocumento.getDOMImplementation();

            Document documento = implementation.createDocument(null, "carnet", null);
            Element carnet = documento.getDocumentElement();
            documento.setXmlVersion("1.0");

            Element id, fechaexp, expedidoen, peregrino, nombre, nacionalidad, hoy, distanciatotal, estancias, estancia,
                    idestancia, fecha, parada, vip;
            Text idValor, fechaexpValor, expedidoenValor, nomValor, nacValor, hoyValor, distotalValor, idestanciaValor,
                    fechaValor, paradaValor, vipValor;

            id = documento.createElement("id");
            carnet.appendChild(id);
            idValor = documento.createTextNode(String.valueOf(p.getIdPeregrino()));
            id.appendChild(idValor);

            fechaexp = documento.createElement("fechaexp");
            carnet.appendChild(fechaexp);
            fechaexpValor = documento.createTextNode(p.getCarnet().getFechaExp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            fechaexp.appendChild(fechaexpValor);

            expedidoen = documento.createElement("expedidoen");
            carnet.appendChild(expedidoen);
            expedidoenValor = documento.createTextNode(p.getCarnet().getParadaInicial().getNombre());
            expedidoen.appendChild(expedidoenValor);

            peregrino = documento.createElement("peregrino");
            carnet.appendChild(peregrino);

            nombre = documento.createElement("nombre");
            peregrino.appendChild(nombre);
            nomValor = documento.createTextNode(p.getNombre());
            nombre.appendChild(nomValor);

            nacionalidad = documento.createElement("nacionalidad");
            peregrino.appendChild(nacionalidad);
            nacValor = documento.createTextNode(p.getNacionalidad());
            nacionalidad.appendChild(nacValor);

            hoy = documento.createElement("hoy");
            carnet.appendChild(hoy);
            hoyValor = documento.createTextNode(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            hoy.appendChild(hoyValor);

            distanciatotal = documento.createElement("distanciatotal");
            carnet.appendChild(distanciatotal);
            distotalValor = documento.createTextNode(String.valueOf(p.getCarnet().getDistancia()));
            distanciatotal.appendChild(distotalValor);

            estancias = documento.createElement("estancias");
            carnet.appendChild(estancias);

            if (p.getEstancias() != null) {
                for (Estancia i : p.getEstancias()) {
                    //crear todas las etiquetas necesarias
                    estancia = documento.createElement("estancia");
                    estancias.appendChild(estancia);

                    idestancia = documento.createElement("id");
                    estancia.appendChild(idestancia);
                    idestanciaValor = documento.createTextNode(String.valueOf(i.getIdEstancia()));
                    idestancia.appendChild(idestanciaValor);

                    fecha = documento.createElement("fecha");
                    estancia.appendChild(fecha);
                    fechaValor = documento.createTextNode(i.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    fecha.appendChild(fechaValor);

                    parada = documento.createElement("parada");
                    estancia.appendChild(parada);
                    paradaValor = documento.createTextNode(i.getParada().getNombre());
                    parada.appendChild(paradaValor);

                    if (i.isVip()) {
                        vip = documento.createElement("vip");
                        estancia.appendChild(vip);
                        vipValor = documento.createTextNode("vip");
                        vip.appendChild(vipValor);

                    }

                }

            } else {
                Text valorE;
                valorE = documento.createTextNode("No hay paradas actualmente");
                estancias.appendChild(valorE);
            }

            Source fuente = new DOMSource(documento);
            File fichero = new File(path);
            Result resultado = new StreamResult(fichero);
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            transformador.transform(fuente, resultado);


        } catch (ParserConfigurationException ex) {
            System.out.println("Se ha producido una ParseConfigurationException: " + ex.getMessage());
        } catch (TransformerConfigurationException ex) {
            System.out.println("Se ha producido una TransformerConfigurationException e: " + ex.getMessage());
        } catch (TransformerException ex) {
            System.out.println("Se ha producido una TransformerException: " + ex.getMessage());
        }

    }

}




