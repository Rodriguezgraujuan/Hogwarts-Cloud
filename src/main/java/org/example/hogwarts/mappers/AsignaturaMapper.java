package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.AsignaturaDTO;
import org.example.hogwarts.model.Asignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

    public AsignaturaDTO toDTO(Asignatura asignatura) {
        if (asignatura == null) return null;

        String nombreProfesor = asignatura.getProfesor() != null ? asignatura.getProfesor().getNombre() : null;

        return new AsignaturaDTO(
                asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getAula(),
                asignatura.getObligatoria(),
                nombreProfesor
        );
    }
}

