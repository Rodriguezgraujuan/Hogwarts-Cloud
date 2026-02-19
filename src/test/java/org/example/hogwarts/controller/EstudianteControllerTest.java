package org.example.hogwarts.controller;

import org.example.hogwarts.DTOs.EstudianteCreateDTO;
import org.example.hogwarts.mappers.EstudianteMapper;
import org.example.hogwarts.repository.EstudianteRepository;
import org.example.hogwarts.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstudianteService estudianteService;

    @MockitoBean(name = "estudianteMapper")
    private EstudianteMapper estudianteMapper;

    @MockitoBean(name = "createMapper")
    private EstudianteMapper createMapper;

    @MockitoBean
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearEstudiante_curso10_devuelve400() throws Exception {
        EstudianteCreateDTO dto = new EstudianteCreateDTO();
        dto.setNombre("Draco Malfoy");
        dto.setAnyoCurso(10);

        String json = objectMapper.writeValueAsString(dto);

        when(estudianteService.save(createMapper.toEntity(dto), null))
                .thenThrow(new RuntimeException("Curso no permitido"));

        mockMvc.perform(post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
