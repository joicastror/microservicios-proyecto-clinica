package com.clinica.autenticacion.dto;

import lombok.Data;

@Data
public class UsuarioMostrarDTO {
    private Integer id;
    private String rut;
    private String nombre;
    private String email;
    private String rol;             //"PACIENTE", "MEDICO", "ADMIN"
    private String especialidad;    //Solo para médicos
    private Boolean estado;
}
