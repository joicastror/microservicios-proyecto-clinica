package com.clinica.autenticacion.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 8, message = "La clave debe tener al menos 8 caracteres")
    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @NotBlank(message = "El rol es obligatorio")
    private String rol; //"PACIENTE", "MEDICO", "ADMIN"

    private String especialidad; //Opcional, solo para médicos

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
}
