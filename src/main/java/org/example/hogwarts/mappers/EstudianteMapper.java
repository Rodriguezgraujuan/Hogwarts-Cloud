package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.AsignaturaCalificacionDTO;
import org.example.hogwarts.DTOs.EstudianteCreateDTO;
import org.example.hogwarts.DTOs.EstudianteDTO;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Mascota;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstudianteMapper {

    private final MascotaMapper mascotaMapper;
    private final AsignaturaCalificacionMapper asignaturaCalificacionMapper;

    public EstudianteMapper(MascotaMapper mascotaMapper,
                            AsignaturaCalificacionMapper asignaturaCalificacionMapper) {
        this.mascotaMapper = mascotaMapper;
        this.asignaturaCalificacionMapper = asignaturaCalificacionMapper;
    }

    public EstudianteDTO toDTO(Estudiante estudiante) {
        if (estudiante == null) return null;

        String nombreCasa = estudiante.getCasa() != null ? estudiante.getCasa().getNombre() : null;

        List<AsignaturaCalificacionDTO> asignaturasDTO = estudiante.getAsignaturasConNotas() != null
                ? estudiante.getAsignaturasConNotas().stream()
                .map(asignaturaCalificacionMapper::toDTO)
                .collect(Collectors.toList())
                : null;

        return new EstudianteDTO(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getAnyoCurso(),
                estudiante.getFechaNacimiento(),
                nombreCasa,
                mascotaMapper.toDTO(estudiante.getMascota()),
                asignaturasDTO
        );
    }

    public Estudiante toEntity(EstudianteCreateDTO dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(LocalDate.parse(dto.getFechaNacimiento()));

        if (dto.getMascota() != null) {
            Mascota mascota = new Mascota();
            mascota.setNombre(dto.getMascota().getNombre());
            mascota.setTipo(dto.getMascota().getEspecie());
            mascota.setEstudiante(estudiante); // asociar al estudiante
            estudiante.setMascota(mascota);
        }

        return estudiante;
    }
}

