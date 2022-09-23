package com.example.miProyectoIntegradorV2.Service;

import com.example.miProyectoIntegradorV2.Entities.Usuario;
import com.example.miProyectoIntegradorV2.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(username);
        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }
        else {
            throw new UsernameNotFoundException("No existe un usuario con ese username: " + username);
        }
    }
}
