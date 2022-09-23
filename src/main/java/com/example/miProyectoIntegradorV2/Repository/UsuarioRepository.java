package com.example.miProyectoIntegradorV2.Repository;

import com.example.miProyectoIntegradorV2.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("select u from Usuario u where u.email=?1")
    Optional<Usuario> findUsuarioByEmail(String email);
}
