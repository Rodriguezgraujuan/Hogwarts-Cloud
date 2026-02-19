package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.AsignaturaCalificacionDTO;
import org.example.hogwarts.model.Estudiante_Asignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaCalificacionMapper {

    public AsignaturaCalificacionDTO toDTO(Estudiante_Asignatura ea) {
        if (ea == null) return null;
        return new AsignaturaCalificacionDTO(
                ea.getAsignatura().getNombre(),
                ea.getNota()
        );
    }
}
