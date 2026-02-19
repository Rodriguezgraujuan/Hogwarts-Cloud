package org.example.hogwarts.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDTO {

    private Long id;
    @NotBlank(message = "El nombre de la mascota es obligatorio")
    private String nombre;
    @NotBlank(message = "La especie es obligatoria")
    private String especie;
    private String estudiante;
}
