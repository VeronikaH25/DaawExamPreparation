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
    private CarService carService; 

    // CAMBIO: Ahora recibe String carId
    public Maintenance addMaintenanceToCar(String carId, Maintenance maintenance) {
        // 1. Validamos que el coche existe (getCarById ya maneja el 404)
        Car car = carService.getCarById(carId);
        
        // 2. Vinculamos el ID del coche al mantenimiento
        maintenance.setCarId(car.getId());
        
        // 3. Opcional: Si quieres que el coche también guarde el mantenimiento en su lista
        car.getMaintenances().add(maintenance);
        carService.saveCar(car); // Actualizamos el coche en Atlas
        
        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }
}