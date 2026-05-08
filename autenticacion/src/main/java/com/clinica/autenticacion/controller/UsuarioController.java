package com.clinica.autenticacion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.autenticacion.dto.UsuarioCrearDTO;
import com.clinica.autenticacion.dto.UsuarioMostrarDTO;
import com.clinica.autenticacion.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioMostrarDTO> registrarUsuario(@Valid @RequestBody UsuarioCrearDTO dto) {
        UsuarioMostrarDTO usuarioRegistrado = usuarioService.registrarUsuario(dto);
        return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
    }
    

}
