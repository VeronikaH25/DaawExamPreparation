package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Crear un coche
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Buscar por ID (Requisito: Manejo de recurso inexistente -> 404)
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Car not found with id: " + id));
    }

    // Listar todos los coches
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}