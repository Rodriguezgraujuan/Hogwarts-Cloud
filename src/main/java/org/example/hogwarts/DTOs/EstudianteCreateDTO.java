package org.example.hogwarts.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El año de curso es obligatorio")
    @Min(value = 1, message = "El año debe ser mayor o igual a 1")
    @Max(value = 7, message = "El año debe ser menor o igual a 7")
    private Integer anyoCurso;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String fechaNacimiento;
    @NotNull(message = "La casaId es obligatoria")
    private Long casaId;
    @Valid
    private MascotaDTO mascota;
}
