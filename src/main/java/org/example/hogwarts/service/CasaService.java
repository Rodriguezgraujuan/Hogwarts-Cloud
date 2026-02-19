package org.example.hogwarts.service;

import org.example.hogwarts.DTOs.CasaDTO;
import org.example.hogwarts.mappers.CasaMapper;
import org.example.hogwarts.model.Casa;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Profesor;
import org.example.hogwarts.repository.CasaRepository;
import org.example.hogwarts.repository.EstudianteRepository;
import org.example.hogwarts.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasaService {

    private final CasaRepository repository;
    private final CasaMapper casaMapper;
    private final ProfesorRepository profesorRepository;

    public CasaService(CasaRepository repository, CasaMapper casaMapper, ProfesorRepository profesorRepository) {
        this.repository = repository;
        this.casaMapper = casaMapper;
        this.profesorRepository = profesorRepository;
    }

    public List<CasaDTO> findAllDTO() {
        return repository.findAll()
                .stream()
                .map(casaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CasaDTO findByIdDTO(Long id) {
        return repository.findById(id)
                .map(casaMapper::toDTO)
                .orElse(null);
    }

    public Casa save(Casa casa) {
        if (casa.getProfesor() != null) {
            Optional<Profesor> profesor;
            profesor = profesorRepository.findById(casa.getProfesor().getId());
            casa.setProfesor(profesor.orElse(null));
        }
        return repository.save(casa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
