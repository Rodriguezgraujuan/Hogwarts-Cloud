package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.ProfesorDTO;
import org.example.hogwarts.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public ProfesorDTO toDTO(Profesor profesor) {
        if (profesor == null) return null;

        String nombreAsignatura = profesor.getAsignatura() != null ? profesor.getAsignatura().getNombre() : null;

        return new ProfesorDTO(
                profesor.getId(),
                profesor.getNombre(),
                nombreAsignatura,
                profesor.getFechaInicio()
        );
    }
}
