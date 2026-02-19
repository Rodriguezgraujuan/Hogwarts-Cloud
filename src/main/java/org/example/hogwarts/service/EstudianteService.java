package org.example.hogwarts.service;



import org.example.hogwarts.DTOs.EstudianteDTO;
import org.example.hogwarts.DTOs.EstudianteUpdateDTO;
import org.example.hogwarts.DTOs.MascotaDTO;
import org.example.hogwarts.mappers.EstudianteMapper;
import org.example.hogwarts.mappers.MascotaMapper;
import org.example.hogwarts.model.Casa;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Mascota;
import org.example.hogwarts.repository.CasaRepository;
import org.example.hogwarts.repository.EstudianteRepository;
import org.example.hogwarts.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EstudianteService {

    private final EstudianteRepository repository;
    private final EstudianteMapper estudianteMapper;
    private final MascotaRepository mascotaRepository;
    private final CasaRepository casaRepository;
    private final MascotaMapper mascotaMapper;

    public EstudianteService(EstudianteRepository repository, EstudianteMapper estudianteMapper, MascotaRepository mascotaRepository, CasaRepository casaRepository, MascotaMapper mascotaMapper) {
        this.repository = repository;
        this.estudianteMapper = estudianteMapper;
        this.mascotaRepository = mascotaRepository;

        this.casaRepository = casaRepository;
        this.mascotaMapper = mascotaMapper;
    }

    public List<EstudianteDTO> findAllDTO() {
        return repository.findAll()
                .stream()
                .map(estudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EstudianteDTO findByIdDTO(Long id) {
        return repository.findById(id)
                .map(estudianteMapper::toDTO)
                .orElse(null);
    }

    public Estudiante save(Estudiante estudiante, Long idCasa) {
        if (idCasa != null) {
            Casa casa = casaRepository.findById(idCasa)
                    .orElseThrow(() -> new RuntimeException("Casa no encontrada"));
            estudiante.setCasa(casa);
        }
        if (estudiante.getMascota() != null) {
            estudiante.getMascota().setEstudiante(estudiante);
        }
        return repository.save(estudiante);
    }

    public void delete(Long id) {
        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (estudiante.getMascota() != null) {
            Mascota mascota = estudiante.getMascota();
            mascota.setEstudiante(null);
            mascotaRepository.delete(mascota);
            estudiante.setMascota(null);
        }

        repository.delete(estudiante);
    }

    public EstudianteDTO update(Long id, EstudianteUpdateDTO dto) {

        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(LocalDate.parse(dto.getFechaNacimiento()));

        MascotaDTO mascotaDTO = dto.getMascota();

        if (estudiante.getMascota() != null) {
            Mascota actual = estudiante.getMascota();
            actual.setEstudiante(null);
            mascotaRepository.delete(actual);
            estudiante.setMascota(null);
        }

        if (mascotaDTO != null) {
            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setNombre(mascotaDTO.getNombre());
            nuevaMascota.setTipo(mascotaDTO.getEspecie());
            nuevaMascota.setEstudiante(estudiante);
            estudiante.setMascota(nuevaMascota);
        }


        repository.save(estudiante);

        return estudianteMapper.toDTO(estudiante);
    }
}
