package org.example.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String fundador;

    private String fantasma;

    @OneToMany(mappedBy = "casa")
    @JsonIgnoreProperties("casa")
    private List<Estudiante> estudiantes;

    @OneToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
}


