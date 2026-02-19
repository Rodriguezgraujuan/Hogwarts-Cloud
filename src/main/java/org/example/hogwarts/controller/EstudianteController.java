package org.example.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.hogwarts.DTOs.ActualizarCalificacionDTO;
import org.example.hogwarts.DTOs.EstudianteCreateDTO;
import org.example.hogwarts.DTOs.EstudianteDTO;
import org.example.hogwarts.DTOs.EstudianteUpdateDTO;
import org.example.hogwarts.mappers.EstudianteMapper;
import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Estudiante_Asignatura;
import org.example.hogwarts.repository.EstudianteRepository;
import org.example.hogwarts.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository repository;
    private final EstudianteService service;
    private final EstudianteMapper estudianteMapper;
    private final EstudianteMapper createMapper;

    public EstudianteController(EstudianteService service,
                                EstudianteMapper estudianteMapper,
                                EstudianteMapper createMapper,
                                EstudianteRepository estudianteRepository) {
        this.service = service;
        this.estudianteMapper = estudianteMapper;
        this.createMapper = createMapper;
        this.repository = estudianteRepository;
    }

    @GetMapping
    public List<EstudianteDTO> getAll() {
        return service.findAllDTO();
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve un usuario específico según el ID proporcionado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public EstudianteDTO getById(@PathVariable Long id) {
        return service.findByIdDTO(id);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@RequestBody @Valid EstudianteCreateDTO dto) {
        Estudiante estudiante = createMapper.toEntity(dto);
        Estudiante saved = service.save(estudiante, dto.getCasaId());
        EstudianteDTO response = estudianteMapper.toDTO(saved);
        return ResponseEntity.status(201).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid EstudianteUpdateDTO dto) {

        EstudianteDTO actualizado = service.update(id, dto);
        return ResponseEntity.ok(actualizado);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/calificaciones/{asignaturaId}")
    public ResponseEntity<String> actualizarCalificacion(@PathVariable Long id,
                                                         @PathVariable Long asignaturaId,
                                                         @RequestBody ActualizarCalificacionDTO dto) {
        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Estudiante_Asignatura ea = estudiante.getAsignaturasConNotas().stream()
                .filter(x -> x.getAsignatura().getId().equals(asignaturaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        ea.setNota(dto.getCalificacion());
        repository.save(estudiante);

        return ResponseEntity.ok("Calificación actualizada correctamente");
    }

}
