package com.example.backend.controller;

import com.example.backend.model.Car;
import com.example.backend.model.Maintenance;
import com.example.backend.service.CarService;
import com.example.backend.service.MaintenanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Opcional: ayuda a organizar, pero puedes quitarlo si prefieres rutas directas
public class ApiController {

    @Autowired
    private CarService carService;

    @Autowired
    private MaintenanceService maintenanceService;

    // 1. POST /cars -> Crear un coche
    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        // Devolvemos 201 Created como pide el examen
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }

    // 2. GET /cars/{id} -> Obtener un coche por ID
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        // El service ya lanza el 404 si no existe
        return ResponseEntity.ok(carService.getCarById(id));
    }

    // 3. POST /cars/{id}/maintenance -> Crear mantenimiento para un coche
    @PostMapping("/cars/{id}/maintenance")
    public ResponseEntity<Maintenance> addMaintenance(@PathVariable Long id, @Valid @RequestBody Maintenance maintenance) {
        return new ResponseEntity<>(maintenanceService.addMaintenanceToCar(id, maintenance), HttpStatus.CREATED);
    }

    // 4. GET /maintenance -> Obtener todos los mantenimientos
    @GetMapping("/maintenance")
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAll());
    }
}