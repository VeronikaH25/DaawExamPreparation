package com.example.backend.repository;

import com.example.backend.model.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository; // Importe de MongoDB
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {
    // Ahora hereda de MongoRepository y el ID es de tipo String
}