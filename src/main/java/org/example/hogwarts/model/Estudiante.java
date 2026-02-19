package org.example.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int anyoCurso = 1;

    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "casa_id")
    private Casa casa;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante_Asignatura> asignaturasConNotas;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Mascota mascota;
}
