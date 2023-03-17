package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.Parada;
import com.example.PeregrinosFX.controller.RegistroController;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExitDB {
    private Collection col = null;
    private XMLResource res = null;

    public String getUrl() throws IOException {
        Properties propiedades = new Properties();
        FileInputStream fis;
        fis = new FileInputStream("../PeregrinosFXMiguel/exitdb.properties");
        propiedades.load(fis);
        return propiedades.getProperty("exit.url");
    }

    public String getDriver() throws IOException {
        Properties propiedades = new Properties();
        FileInputStream fis;
        fis = new FileInputStream("../PeregrinosFXMiguel/exitdb.properties");
        propiedades.load(fis);
        return propiedades.getProperty("exit.driver");
    }

    public String[] verDatosCol(Parada parada, ListView listView) {
        String resources[];
        try {
            //Iniciar driver
            Class cl = Class.forName(getDriver());
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            //Conectar con coleccion
            col = DatabaseManager.getCollection(getUrl(), "admin", "");

           // Nombre de la subcolección
            String nombreSubColeccion = "" + parada.getIdParada();

            Collection subCol = col.getChildCollection(nombreSubColeccion);

            resources = new String[0];
            try {
                resources = subCol.listResources();
            } catch (XMLDBException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < resources.length; i++) {
                listView.getItems().add(resources[i]);
            }
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            listView.getItems().add("No hay peregrinos expedidos en esta parada");
            return resources = new String[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resources;
    }

    public void guardar(File file, Parada parada) {
        try {
            //Iniciar driver
            Class cl = Class.forName(getDriver());
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            //Conectar con coleccion
            col = DatabaseManager.getCollection(getUrl(), "admin", "");

            String nombreSubColeccion = "" + parada.getIdParada(); // Nombre de la subcolección

            Collection subCol = col.getChildCollection(nombreSubColeccion);

            while (subCol == null) {
                CollectionManagementService mgtService = (CollectionManagementService)
                        col.getService("CollectionManagementService", "1.0");
                try {
                    mgtService.createCollection("" + parada.getIdParada());
                } catch (XMLDBException e) {
                    e.printStackTrace();
                }
                subCol = col.getChildCollection(nombreSubColeccion);
            }
            if (subCol != null) {
                res = (XMLResource) subCol.createResource(file.getName(), "XMLResource");
                res.setContent(file);
                subCol.storeResource(res);
            }
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (col != null) {
            try {
                col.close();
                /// Apagar el servidor ExistDB
                //DatabaseInstanceManager manager = (DatabaseInstanceManager) col.getService("DatabaseInstanceManager", "1.0");
                //manager.shutdown();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    public String buscarPorParadaNombre(Parada parada, String nombreXML) {
        String ret = "";
        try {
            Class cl = Class.forName(getDriver());
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            col = DatabaseManager.getCollection(getUrl(), "admin", "");

            String ruta = "xmldb:exist://localhost:8080/exist/xmlrpc/db/carnets";

            String namesubcol = "" + parada.getIdParada();
            Collection subCol = col.getChildCollection(namesubcol);

            Resource res = subCol.getResource(nombreXML);

            ret = (String) res.getContent();


        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;

    }

}