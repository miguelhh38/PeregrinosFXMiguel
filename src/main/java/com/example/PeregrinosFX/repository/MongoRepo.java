package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.CarnetBackup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepo extends MongoRepository<CarnetBackup, String> {

}
