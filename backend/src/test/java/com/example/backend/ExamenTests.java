package com.example.backend;

import com.example.backend.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamenTests {

   // @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFullFlow() {
        // CAMBIO AQUÍ: Usa solo la ruta, sin http ni localhost
        String baseUrl = "/api/cars"; 

        // --- PASO 1: Crear coche ---
        Car carRequest = new Car("1234-XYZ", 2026);
        
        // TestRestTemplate ya sabe a qué puerto llamar automáticamente
        ResponseEntity<Car> postResponse = restTemplate.postForEntity(baseUrl, carRequest, Car.class);

        // Verificaciones
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody()).isNotNull();
        
        // Importante: Asegúrate de que el ID que devuelve no sea nulo
        Long createdId = postResponse.getBody().getId();
        assertThat(createdId).isNotNull();

        // --- PASO 2: Recuperar mediante GET ---
        String getUrl = baseUrl + "/" + createdId;
        ResponseEntity<Car> getResponse = restTemplate.getForEntity(getUrl, Car.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getPlate()).isEqualTo("1234-XYZ");
    }
}