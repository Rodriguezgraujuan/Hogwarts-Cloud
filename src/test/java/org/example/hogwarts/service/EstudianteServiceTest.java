package org.example.hogwarts.service;

import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante harry;

    @BeforeEach
    void setUp() {
        harry = new Estudiante();
        harry.setId(1L);
        harry.setNombre("Harry Potter");
        harry.setAnyoCurso(5);
    }

    @Test
    void expulsarHarryPotter() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(harry));

        estudianteService.delete(1L);

        verify(estudianteRepository, times(1)).delete(harry);
    }
}

