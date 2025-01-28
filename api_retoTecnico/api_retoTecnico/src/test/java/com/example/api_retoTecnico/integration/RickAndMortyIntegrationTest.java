package com.example.api_retoTecnico.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class RickAndMortyIntegrationTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void testRealAPIConnection() {
        WebClient webClient = webClientBuilder.baseUrl("https://rickandmortyapi.com/api").build();
        String response = webClient.get()
                .uri("/character/1")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assertNotNull(response, "La respuesta no debe ser nula");
        assertTrue(response.contains("Rick Sanchez"), "La respuesta debe contener el nombre 'Rick Sanchez'");
    }
}
