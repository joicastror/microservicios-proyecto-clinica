package com.clinica.autenticacion.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El RUT es obligatorio")
    private String rut;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "La clave es obligatoria")
    private String clave;

    @NotNull(message = "El rol es obligatorio")
    private String rol;

    @NotNull(message = "El teléfono es obligatorio")
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;


}
