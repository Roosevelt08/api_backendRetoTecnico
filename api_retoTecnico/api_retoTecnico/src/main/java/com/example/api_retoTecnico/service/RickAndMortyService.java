package com.example.api_retoTecnico.service;

import com.example.api_retoTecnico.exception.CustomException;
import com.example.api_retoTecnico.model.Character;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class RickAndMortyService {

    private final WebClient webClient;

    public RickAndMortyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public List<Character> getAllCharacters() {
        try {
            return webClient.get()
                    .uri("/character")
                    .retrieve()
                    .bodyToMono(CharacterListResponse.class)
                    .map(CharacterListResponse::getResults)
                    .block();
        } catch (WebClientResponseException e) {
            throw new CustomException("Error al obtener los personajes.");
        }
    }

    public Character getCharacterById(int id) {
        try {
            return webClient.get()
                    .uri("/character/{id}", id)
                    .retrieve()
                    .bodyToMono(Character.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new CustomException("Personaje no encontrado.");
        }
    }

    private static class CharacterListResponse {
        private List<Character> results;

        public List<Character> getResults() {
            return results;
        }
    }
}
