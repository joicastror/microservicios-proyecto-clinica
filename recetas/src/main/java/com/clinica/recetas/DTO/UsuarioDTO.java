package com.clinica.recetas.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;
    private String rut;
    private String nombre;
    private String email;
    private String rol;
    private String especialidad;
    private Boolean estado;
}