package org.example.hogwarts.controller;

import org.example.hogwarts.DTOs.CasaDTO;
import org.example.hogwarts.model.Casa;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Mascota;
import org.example.hogwarts.repository.CasaRepository;
import org.example.hogwarts.service.CasaService;
import org.example.hogwarts.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casas")
public class CasaController {

    @Autowired
    private CasaService service;

    @GetMapping
    public List<CasaDTO> getAll() {
        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public CasaDTO getById(@PathVariable Long id) {
        return service.findByIdDTO(id);
    }

    @PostMapping
    public Casa create(@RequestBody Casa casa) {
        return service.save(casa);
    }

    @PutMapping("/{id}")
    public Casa update(@PathVariable Long id, @RequestBody Casa casa) {
        casa.setId(id);
        return service.save(casa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

