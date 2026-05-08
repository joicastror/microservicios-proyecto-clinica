package com.clinica.autenticacion.service;

import org.springframework.stereotype.Service;

import com.clinica.autenticacion.dto.UsuarioCrearDTO;
import com.clinica.autenticacion.dto.UsuarioMostrarDTO;
import com.clinica.autenticacion.model.Usuario;
import com.clinica.autenticacion.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioMostrarDTO registrarUsuario(UsuarioCrearDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setRut(dto.getRut());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());

        usuario.setClave(dto.getClave());
        usuario.setEstado(true);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertiraDTO(usuarioGuardado);
    }

    private UsuarioMostrarDTO convertiraDTO(Usuario usuario) {
        UsuarioMostrarDTO dto = new UsuarioMostrarDTO();
        dto.setId(usuario.getId());
        dto.setRut(usuario.getRut());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
