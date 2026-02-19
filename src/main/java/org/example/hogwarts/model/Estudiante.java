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

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnyoCurso() {
        return anyoCurso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Casa getCasa() {
        return casa;
    }

    public List<Estudiante_Asignatura> getAsignaturasConNotas() {
        return asignaturasConNotas;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnyoCurso(int anyoCurso) {
        this.anyoCurso = anyoCurso;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public void setAsignaturasConNotas(List<Estudiante_Asignatura> asignaturasConNotas) {
        this.asignaturasConNotas = asignaturasConNotas;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
