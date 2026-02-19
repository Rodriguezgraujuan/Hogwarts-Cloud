package org.example.hogwarts.repository;

import org.example.hogwarts.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {}

