package org.example.hogwarts.mappers;

import org.example.hogwarts.DTOs.MascotaDTO;
import org.example.hogwarts.model.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {

    public MascotaDTO toDTO(Mascota mascota) {
        if (mascota == null) return null;

        String nombreEstudiante = mascota.getEstudiante() != null ? mascota.getEstudiante().getNombre() : null;

        return new MascotaDTO(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getTipo(), // tipo → especie
                nombreEstudiante
        );
    }

    public Mascota toEntity(MascotaDTO dto) {
        if (dto == null) return null;

        Mascota mascota = new Mascota();
        mascota.setId(dto.getId());
        mascota.setNombre(dto.getNombre());
        mascota.setTipo(dto.getEspecie()); // especie → tipo
        return mascota;
    }
}
