package com.example.PeregrinosFX.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Carnets Backup")
public class CarnetBackup {
    public String file;
    public ArrayList<String> carnets;

    public CarnetBackup(String file, ArrayList<String> carnets) {
        this.file = file;
        this.carnets = carnets;
    }

    public CarnetBackup() {

    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ArrayList<String> getCarnets() {
        return carnets;
    }

    public void setCarnets(ArrayList<String> carnetsB) {
        this.carnets = carnets;
    }
}
