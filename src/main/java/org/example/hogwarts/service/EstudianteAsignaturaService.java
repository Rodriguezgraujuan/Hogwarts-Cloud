package org.example.hogwarts.service;

import org.example.hogwarts.DTOs.AsignaturaCalificacionDTO;
import org.example.hogwarts.mappers.AsignaturaCalificacionMapper;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteAsignaturaService {

    private final EstudianteRepository estudianteRepository;
    private final AsignaturaCalificacionMapper asignaturaCalificacionMapper;

    // Constructor correcto, ambos inyectados
    public EstudianteAsignaturaService(EstudianteRepository estudianteRepository,
                                       AsignaturaCalificacionMapper asignaturaCalificacionMapper) {
        this.estudianteRepository = estudianteRepository;
        this.asignaturaCalificacionMapper = asignaturaCalificacionMapper;
    }

    public List<AsignaturaCalificacionDTO> obtenerCalificaciones(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Convertimos cada EstudianteAsignatura a DTO
        return estudiante.getAsignaturasConNotas().stream()
                .map(asignaturaCalificacionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
