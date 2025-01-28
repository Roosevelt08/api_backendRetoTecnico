package com.example.api_retoTecnico.controller;

import com.example.api_retoTecnico.model.Character;
import com.example.api_retoTecnico.service.RickAndMortyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final RickAndMortyService service;

    public CharacterController(RickAndMortyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Character> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return service.getCharacterById(id);
    }
}
