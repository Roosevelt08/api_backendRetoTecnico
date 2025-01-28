package com.example.api_retoTecnico.service;

import static org.mockito.Mockito.*;
import com.example.api_retoTecnico.model.Character;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class RickAndMortyServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    @InjectMocks
    private RickAndMortyService rickAndMortyService;

    @Test
    void testGetCharacterById_ReturnsCharacter() {
        // Mock de respuesta JSON de la API
        String mockResponse = "{ \"id\": 1, \"name\": \"Rick Sanchez\", \"status\": \"Alive\" }";
        
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(mockResponse));

        // Llamar al servicio
        Character result = rickAndMortyService.getCharacterById(1);

        // Validaciones
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Rick Sanchez", result.getName(), "El nombre debe ser Rick Sanchez");
        assertEquals("Alive", result.getStatus(), "El estado debe ser Alive");
    }

    @Test
    void testGetCharacterById_ReturnsNullWhenApiFails() {
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.error(new RuntimeException("API no disponible")));

        // Llamar al servicio
        Character result = rickAndMortyService.getCharacterById(1);

        // Validación de que devuelve null
        assertNull(result, "Debe devolver null cuando la API falla");
    }
}
