package com.example.api_retoTecnico.controller;

import com.example.api_retoTecnico.model.Character;
import com.example.api_retoTecnico.service.RickAndMortyService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(service.getAllCharacters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable int id) {
        return ResponseEntity.ok(service.getCharacterById(id));
    }
}
