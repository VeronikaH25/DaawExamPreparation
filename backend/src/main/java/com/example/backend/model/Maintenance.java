package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// Eliminamos @Entity y @Table. Si quieres que Maintenance sea una colección propia:
@Document(collection = "maintenances")
public class Maintenance {

    @Id // En Mongo el ID es String
    private String id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "El coste es obligatorio")
    private Double cost;

    // En MongoDB NoSQL no usamos @ManyToOne. 
    // Si necesitas referenciar al coche, simplemente guardamos su ID.
    private String carId;

    public Maintenance() {}

    public Maintenance(LocalDate date, String description, Double cost) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }
}