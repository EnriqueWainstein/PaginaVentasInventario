package com._ip.pagina.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com._ip.pagina.Entidades.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}

