package org.example.hogwarts.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {

    private Long id;
    private String nombre;
    private int anyoCurso;
    private LocalDate fechaNacimiento;
    private String casa;
    private MascotaDTO mascota;
    private List<AsignaturaCalificacionDTO> asignaturas;
}

