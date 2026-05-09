package com.clinica.examenes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "examenes")
@AllArgsConstructor
@NoArgsConstructor

public class Examenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del examen es obligatorio")
    private String nombreExamen;

    @NotBlank(message = "El tipo de examen es obligatorio")
    private String tipoExamen;

    @NotNull(message = "La fecha del examen es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser pasada")
    private LocalDate fechaExamen;

    @NotBlank(message = "El resultado es obligatorio")
    private String resultado;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El email no puede estar vacío")
    @Column(unique = true)
    private String email;

    @NotNull(message = "El id del paciente es obligatorio")
    @Positive(message = "El id del paciente debe ser mayor a 0")
    private Integer idPaciente;

    @NotNull(message = "El id del médico es obligatorio")
    @Positive(message = "El id del médico debe ser mayor a 0")
    private Integer idMedico;

}