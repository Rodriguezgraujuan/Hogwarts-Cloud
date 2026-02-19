package org.example.hogwarts.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CasaDTO {

    private Long id;
    private String nombre;
    private String fundador;
    private String fantasma;
    private ProfesorDTO jefe;
    private List<String> estudiantes;

}