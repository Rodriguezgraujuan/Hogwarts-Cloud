package org.example.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String aula;

    private Boolean obligatoria = false;

    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante_Asignatura> estudiantesConNotas;

    @OneToOne(mappedBy = "asignatura")
    @JsonIgnore
    private Profesor profesor;
}

