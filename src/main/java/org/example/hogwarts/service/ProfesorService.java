package org.example.hogwarts.service;

import org.example.hogwarts.DTOs.ProfesorDTO;
import org.example.hogwarts.mappers.ProfesorMapper;
import org.example.hogwarts.model.Asignatura;
import org.example.hogwarts.model.Mascota;
import org.example.hogwarts.model.Profesor;
import org.example.hogwarts.repository.AsignaturaRepository;
import org.example.hogwarts.repository.MascotaRepository;
import org.example.hogwarts.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;
    private final ProfesorMapper profesorMapper;
    private final AsignaturaRepository asignaturaRepository;

    public ProfesorService(ProfesorRepository repository, ProfesorMapper profesorMapper, AsignaturaRepository asignaturaRepository) {
        this.repository = repository;
        this.profesorMapper = profesorMapper;
        this.asignaturaRepository = asignaturaRepository;
    }

    public List<ProfesorDTO> findAllDTO() {
        return repository.findAll()
                .stream()
                .map(profesorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfesorDTO findByIdDTO(Long id) {
        return repository.findById(id)
                .map(profesorMapper::toDTO)
                .orElse(null);
    }

    public Profesor save(Profesor profesor) {
        if (profesor.getAsignatura() != null) {
            Optional<Asignatura> asignatura;
            asignatura =  asignaturaRepository.findById(profesor.getAsignatura().getId());
            profesor.setAsignatura(asignatura.orElse(null));
        }
        return repository.save(profesor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
