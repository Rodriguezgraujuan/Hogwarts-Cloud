package org.example.hogwarts.controller;

import org.example.hogwarts.DTOs.ProfesorDTO;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Profesor;
import org.example.hogwarts.repository.ProfesorRepository;
import org.example.hogwarts.service.EstudianteService;
import org.example.hogwarts.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService service;

    @GetMapping
    public List<ProfesorDTO> getAll() {
        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public ProfesorDTO getById(@PathVariable Long id) {
        return service.findByIdDTO(id);
    }

    @PostMapping
    public Profesor create(@RequestBody Profesor profesor) {
        return service.save(profesor);
    }

    @PutMapping("/{id}")
    public Profesor update(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        return service.save(profesor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

