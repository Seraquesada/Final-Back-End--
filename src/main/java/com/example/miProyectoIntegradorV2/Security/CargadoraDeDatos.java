package com.example.miProyectoIntegradorV2.Security;

import com.example.miProyectoIntegradorV2.Entities.RollUsuario;
import com.example.miProyectoIntegradorV2.Entities.Usuario;
import com.example.miProyectoIntegradorV2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = "digital";
        String passHash= bCryptPasswordEncoder.encode(pass);
        // ROL USER
        // El index es indexUser
        // no se como hacer que el html base del localhost sea indexUser
        // por lo que te va a romper cuando toques cualquier link
        Usuario usuario = new Usuario();
        usuario.setNombre("rodo");
        usuario.setEmail("rodo");
        usuario.setContraseña(passHash);
        usuario.setRollUsuario(RollUsuario.ROLE_USER);
        usuarioRepository.save(usuario);

        //ROL ADMIN
        // funciona perfecto
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("sera");
        usuario2.setEmail("sera");
        String passAdmin = "digital";
        String passHashAdmin= bCryptPasswordEncoder.encode(passAdmin);
        usuario2.setContraseña(passHashAdmin);
        usuario2.setRollUsuario(RollUsuario.ROLE_ADMIN);
        usuarioRepository.save(usuario2);
    }
}
