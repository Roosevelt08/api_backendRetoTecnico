package com.example.api_retoTecnico.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.api_retoTecnico.service.RickAndMortyService;
import com.example.api_retoTecnico.model.Character;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RickAndMortyService rickAndMortyService;

    @InjectMocks
    private CharacterController characterController;

    @Test
    void testGetCharacterById_ReturnsCharacter() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(characterController).build();

        // Simulación del personaje
        Character mockCharacter = new Character();
        mockCharacter.setId(1);
        mockCharacter.setName("Rick Sanchez");
        mockCharacter.setStatus("Alive");

        when(rickAndMortyService.getCharacterById(1)).thenReturn(mockCharacter);

        mockMvc.perform(get("/api/characters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rick Sanchez"))
                .andExpect(jsonPath("$.status").value("Alive"));
    }
}
