package com.example.miProyectoIntegradorV2.Repository;


import com.example.miProyectoIntegradorV2.Entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
}
