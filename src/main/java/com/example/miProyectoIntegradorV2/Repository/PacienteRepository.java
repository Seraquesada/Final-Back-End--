package com.example.miProyectoIntegradorV2.Repository;


import com.example.miProyectoIntegradorV2.Entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    @Query("select p from Paciente p where p.email=?1")
    Optional<Paciente> findByEmail(String email);
}
