package org.example.hogwarts.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {

    private Long id;
    private String nombre;
    private String asignatura;
    private LocalDate fechaInicio;
}
