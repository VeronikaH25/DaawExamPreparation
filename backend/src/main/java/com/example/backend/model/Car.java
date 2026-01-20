package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.ArrayList;

@Entity // Indica que es una tabla de H2
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La matrícula es obligatoria") // Campo obligatorio
    private String plate;

    @NotNull(message = "El año es obligatorio") // Campo obligatorio
    @Column(name="car_year")
    private Integer year;

    // Relación: Un coche tiene muchos mantenimientos
    // JsonIgnore es vital para que el GET por ID no entre en bucle infinito
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Maintenance> maintenances = new ArrayList<>();

    public Car() {}

    public Car(String plate, Integer year) {
        this.plate = plate;
        this.year = year;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public List<Maintenance> getMaintenances() { return maintenances; }
    public void setMaintenances(List<Maintenance> maintenances) { this.maintenances = maintenances; }
}