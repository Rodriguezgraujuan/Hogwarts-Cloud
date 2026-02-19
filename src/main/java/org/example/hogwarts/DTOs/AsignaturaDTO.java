package org.example.hogwarts.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {

    private Long id;
    private String nombre;
    private String aula;
    private Boolean obligatoria;
    private String profesor;
}
