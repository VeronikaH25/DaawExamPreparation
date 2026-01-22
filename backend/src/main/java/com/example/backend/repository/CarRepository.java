package com.example.backend.repository;

import com.example.backend.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository; // Importe de Mongo
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    // Ahora hereda de MongoRepository y el ID es String
}