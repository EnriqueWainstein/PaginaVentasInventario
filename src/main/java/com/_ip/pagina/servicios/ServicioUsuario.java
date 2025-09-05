package com._ip.pagina.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com._ip.pagina.Entidades.Usuario;
import com._ip.pagina.repositorios.UsuarioRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class ServicioUsuario implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder codificador;

    public ServicioUsuario(UsuarioRepository usuarioRepo, PasswordEncoder codificador) {
        this.usuarioRepo = usuarioRepo;
        this.codificador= codificador;
        
    }
    
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasenia(codificador.encode(usuario.getContrasenia()));
        return usuarioRepo.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUserName(username) // <-- ojo, en tu repo es findByUserName
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUserName())
                .password(usuario.getContrasenia()) // debe estar encriptada con BCrypt
                .roles(usuario.getRol().name())
                .build();
    }

}
