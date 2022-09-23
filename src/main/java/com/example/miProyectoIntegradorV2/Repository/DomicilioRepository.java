package com.example.miProyectoIntegradorV2.Repository;

import com.example.miProyectoIntegradorV2.Entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio,Long> {
}
