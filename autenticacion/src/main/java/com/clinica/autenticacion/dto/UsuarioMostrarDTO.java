package com.clinica.autenticacion.dto;

import lombok.Data;

@Data
public class UsuarioMostrarDTO {
    private Integer id;
    private String rut;
    private String nombre;
    private String email;
    private String rol;
}