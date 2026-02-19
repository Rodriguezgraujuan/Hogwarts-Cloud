package org.example.hogwarts.repository;

import org.example.hogwarts.model.Estudiante;
import org.example.hogwarts.model.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class EstudianteRepositoryTest {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void borrarEstudiante_eliminaMascota() {
        Mascota hedwig = new Mascota();
        hedwig.setNombre("Hedwig");

        Estudiante harry = new Estudiante();
        harry.setNombre("Harry Potter");
        harry.setMascota(hedwig);

        entityManager.persist(hedwig);
        entityManager.persist(harry);
        entityManager.flush();

        Long mascotaId = hedwig.getId();
        Long estudianteId = harry.getId();

        estudianteRepository.deleteById(estudianteId);
        entityManager.flush();

        Mascota mascotaBorrada = entityManager.find(Mascota.class, mascotaId);
        assertNull(mascotaBorrada);
    }
}

