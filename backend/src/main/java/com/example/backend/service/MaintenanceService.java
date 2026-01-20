package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.model.Maintenance;
import com.example.backend.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private CarService carService; // Inyectamos el servicio de coches para validar

    // Crear mantenimiento asociado a un coche específico
    public Maintenance addMaintenanceToCar(Long carId, Maintenance maintenance) {
        // 1. Buscamos el coche usando el servicio anterior. 
        // Si no existe, él mismo lanzará el 404.
        Car car = carService.getCarById(carId);
        
        // 2. Vinculamos el coche al mantenimiento
        maintenance.setCar(car);
        
        // 3. Guardamos el mantenimiento
        return maintenanceRepository.save(maintenance);
    }

    // Listar todos los mantenimientos (Requisito: GET /maintenance)
    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }
}