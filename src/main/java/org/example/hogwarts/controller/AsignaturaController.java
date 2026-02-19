package org.example.hogwarts.controller;

import org.example.hogwarts.DTOs.AsignaturaDTO;
import org.example.hogwarts.model.Asignatura;
import org.example.hogwarts.model.Casa;
import org.example.hogwarts.repository.AsignaturaRepository;
import org.example.hogwarts.service.AsignaturaService;
import org.example.hogwarts.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService service;

    @GetMapping
    public List<AsignaturaDTO> getAll() {
        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public AsignaturaDTO getById(@PathVariable Long id) {
        return service.findByIdDTO(id);
    }
    @PostMapping
    public Asignatura create(@RequestBody Asignatura asignatura) {
        return service.save(asignatura);
    }

    @PutMapping("/{id}")
    public Asignatura update(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        return service.save(asignatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204 si todo va bien
        } catch (RuntimeException e) {
            if (e.getMessage().contains("alumnos")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

