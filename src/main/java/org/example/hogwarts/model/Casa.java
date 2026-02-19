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

    public Profesor getProfesor() {
        return profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public String getFantasma() {
        return fantasma;
    }

    public String getFundador() {
        return fundador;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }

    public void setFantasma(String fantasma) {
        this.fantasma = fantasma;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}



