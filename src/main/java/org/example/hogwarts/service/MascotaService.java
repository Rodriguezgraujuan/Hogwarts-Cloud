package org.example.hogwarts.service;

import org.example.hogwarts.DTOs.MascotaDTO;
import org.example.hogwarts.mappers.MascotaMapper;
import org.example.hogwarts.model.Mascota;
import org.example.hogwarts.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository repository;
    private final MascotaMapper mascotaMapper;

    public MascotaService(MascotaRepository repository, MascotaMapper mascotaMapper) {
        this.repository = repository;
        this.mascotaMapper = mascotaMapper;
    }

    public List<MascotaDTO> findAllDTO() {
        return repository.findAll()
                .stream()
                .map(mascotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MascotaDTO findByIdDTO(Long id) {
        return repository.findById(id)
                .map(mascotaMapper::toDTO)
                .orElse(null);
    }

    public Mascota save(Mascota mascota) {
        return repository.save(mascota);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
