package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;

// Cambiamos @Entity por @Document para MongoDB
@Document(collection = "cars") 
public class Car {

    // En MongoDB los IDs suelen ser String (ObjectId)
    @Id 
    private String id;

    @NotBlank(message = "La matrícula es obligatoria")
    private String plate;

    @NotNull(message = "El año es obligatorio")
    @Field("car_year") // Equivalente a @Column de JPA
    private Integer year;

    /**
     * En MongoDB, para un examen, lo más sencillo es guardar la lista de mantenimientos
     * dentro del mismo documento del coche. Desaparece el 'mappedBy'.
     */
    private List<Maintenance> maintenances = new ArrayList<>();

    public Car() {}

    public Car(String plate, Integer year) {
        this.plate = plate;
        this.year = year;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public List<Maintenance> getMaintenances() { return maintenances; }
    public void setMaintenances(List<Maintenance> maintenances) { this.maintenances = maintenances; }
}