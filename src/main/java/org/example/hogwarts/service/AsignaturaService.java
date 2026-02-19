package org.example.hogwarts.service;

import org.example.hogwarts.DTOs.AsignaturaDTO;
import org.example.hogwarts.mappers.AsignaturaMapper;
import org.example.hogwarts.model.Asignatura;
import org.example.hogwarts.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {

    private final AsignaturaRepository repository;
    private final AsignaturaMapper asignaturaMapper;

    public AsignaturaService(AsignaturaRepository repository, AsignaturaMapper asignaturaMapper) {
        this.repository = repository;
        this.asignaturaMapper = asignaturaMapper;
    }

    public List<AsignaturaDTO> findAllDTO() {
        return repository.findAll()
                .stream()
                .map(asignaturaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AsignaturaDTO findByIdDTO(Long id) {
        return repository.findById(id)
                .map(asignaturaMapper::toDTO)
                .orElse(null);
    }

    public Asignatura save(Asignatura asignatura) {
        return repository.save(asignatura);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
