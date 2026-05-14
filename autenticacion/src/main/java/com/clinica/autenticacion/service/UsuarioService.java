package com.clinica.autenticacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinica.autenticacion.dto.UsuarioMostrarDTO;
import com.clinica.autenticacion.dto.UsuarioRegistroDTO;
import com.clinica.autenticacion.model.Usuario;
import com.clinica.autenticacion.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario guardarUsuario(UsuarioRegistroDTO dto) {
        if (repository.existsByRut(dto.getRut())) {
            throw new IllegalArgumentException("El RUT ya está registrado");
        }

        Usuario usuario = new Usuario();
        
        usuario.setRut(dto.getRut());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        usuario.setEspecialidad(dto.getEspecialidad());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        
        usuario.setEstado(true);

        String passwordEncriptada = passwordEncoder.encode(dto.getClave());
        usuario.setClave(passwordEncriptada);

        return repository.save(usuario);
    }
    

    public Usuario login(String email, String clave) {
        Usuario usuario = repository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (passwordEncoder.matches(clave, usuario.getClave())) {
            return usuario;
        } else {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
    }

    public List<UsuarioMostrarDTO> listarUsuariosDTO() {
        List<Usuario> usuarios = repository.findAll();
        
        List<UsuarioMostrarDTO> listaDTO = new ArrayList<>();

        for (Usuario u : usuarios) {
            UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
            
            dto.setId(u.getId());
            dto.setRut(u.getRut());
            dto.setNombre(u.getNombre());
            dto.setEmail(u.getEmail());
            dto.setRol(u.getRol());
            dto.setEspecialidad(u.getEspecialidad());
            dto.setEstado(u.getEstado());

            listaDTO.add(dto);
        }
        return listaDTO;
    }

    public UsuarioMostrarDTO buscarPorId(Integer id) {
        Usuario u = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
        dto.setId(u.getId());
        dto.setRut(u.getRut());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setEspecialidad(u.getEspecialidad());
        dto.setEstado(u.getEstado());

        return dto;
    }

    public List<UsuarioMostrarDTO> buscarPorNombre(String nombre) {
        List<Usuario> usuarios = repository.findByNombre(nombre);
        List<UsuarioMostrarDTO> listaDTO = new ArrayList<>();

        for (Usuario u : usuarios) {
            UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
            dto.setId(u.getId());
            dto.setRut(u.getRut());
            dto.setNombre(u.getNombre());
            dto.setEmail(u.getEmail());
            dto.setRol(u.getRol());
            dto.setEspecialidad(u.getEspecialidad());
            dto.setEstado(u.getEstado());
            listaDTO.add(dto);
        }
        return listaDTO;
}   

    public UsuarioMostrarDTO buscarPorRut(String rut) {
        Usuario u = repository.findByRut(rut).orElse(null);
        
        if (u == null) return null;

        UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
        dto.setId(u.getId());
        dto.setRut(u.getRut());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setEspecialidad(u.getEspecialidad());
        dto.setEstado(u.getEstado());
        
        return dto;
    }

    public List<UsuarioMostrarDTO> buscarPorRol(String rol) {
        List<Usuario> usuarios = repository.findByRol(rol);
        List<UsuarioMostrarDTO> listaDTO = new ArrayList<>();

        for (Usuario u : usuarios) {
            UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
            dto.setId(u.getId());
            dto.setRut(u.getRut());
            dto.setNombre(u.getNombre());
            dto.setEmail(u.getEmail());
            dto.setRol(u.getRol());
            dto.setEspecialidad(u.getEspecialidad());
            dto.setEstado(u.getEstado());
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    public UsuarioMostrarDTO buscarPorEmail(String email) {
        Usuario u = repository.findByEmail(email).orElse(null);
        
        if (u == null) return null;

        UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
        dto.setId(u.getId());
        dto.setRut(u.getRut());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setEspecialidad(u.getEspecialidad());
        dto.setEstado(u.getEstado());
        
        return dto;
    }

    public Usuario actualizarUsuario(Integer id, UsuarioRegistroDTO usuario) {
        Usuario existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());
        existente.setRol(usuario.getRol());
        existente.setEspecialidad(usuario.getEspecialidad());
        if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
            String passwordEncriptada = passwordEncoder.encode(usuario.getClave());
            existente.setClave(passwordEncriptada);
        }

        return repository.save(existente);
    }

    public void eliminarUsuario(Integer id) {
        repository.deleteById(id);
    }
}
