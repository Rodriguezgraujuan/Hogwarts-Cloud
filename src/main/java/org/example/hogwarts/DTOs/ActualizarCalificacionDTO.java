package org.example.hogwarts.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarCalificacionDTO {
    private Long asignaturaId;
    private Double calificacion;
}
