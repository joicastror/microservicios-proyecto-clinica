package com.clinica.autenticacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.autenticacion.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    List<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByRol(String rol);
    boolean existsByRut(String rut);
    
}
