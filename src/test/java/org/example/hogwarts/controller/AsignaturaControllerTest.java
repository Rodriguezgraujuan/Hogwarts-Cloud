package org.example.hogwarts.controller;

import org.example.hogwarts.service.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AsignaturaController.class)
class AsignaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AsignaturaService asignaturaService;

    @Test
    void eliminarAsignatura_conAlumnos_devuelve409() throws Exception {
        doThrow(new RuntimeException("Asignatura con alumnos"))
                .when(asignaturaService).delete(1L);

        mockMvc.perform(delete("/asignaturas/1"))
                .andExpect(status().isConflict());
    }
}
