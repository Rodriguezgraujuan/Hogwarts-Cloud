package org.example.hogwarts.controller;

import org.example.hogwarts.DTOs.MascotaDTO;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Mascota;
import org.example.hogwarts.repository.MascotaRepository;
import org.example.hogwarts.service.EstudianteService;
import org.example.hogwarts.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService service;

    @GetMapping
    public List<MascotaDTO> getAll() {
        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public MascotaDTO getById(@PathVariable Long id) {
        return service.findByIdDTO(id);
    }

    @PostMapping
    public Mascota create(@RequestBody Mascota mascota) {
        return service.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascota update(@PathVariable Long id, @RequestBody Mascota mascota) {
        mascota.setId(id);
        return service.save(mascota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

