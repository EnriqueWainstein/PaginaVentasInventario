package com._ip.pagina.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com._ip.pagina.Entidades.Usuario;
import com._ip.pagina.repositorios.UsuarioRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class ServicioUsuario implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;

    public ServicioUsuario(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUserName())
                .password(usuario.getContrasenia())
                .roles(usuario.getRol().name()) // toma el enum y lo usa como "ROLE_USER", "ROLE_ADMIN"
                .build();
    }

}
