package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.CasaDTO;
import org.example.hogwarts.model.Casa;
import org.example.hogwarts.model.Estudiante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CasaMapper {

    private final ProfesorMapper profesorMapper;

    public CasaMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public CasaDTO toDTO(Casa casa) {
        if (casa == null) return null;

        List<String> nombresEstudiantes = casa.getEstudiantes() != null
                ? casa.getEstudiantes().stream()
                .map(Estudiante::getNombre)
                .collect(Collectors.toList())
                : null;

        return new CasaDTO(
                casa.getId(),
                casa.getNombre(),
                casa.getFundador(),
                casa.getFantasma(),
                profesorMapper.toDTO(casa.getProfesor()), // Jefe
                nombresEstudiantes
        );
    }
}

