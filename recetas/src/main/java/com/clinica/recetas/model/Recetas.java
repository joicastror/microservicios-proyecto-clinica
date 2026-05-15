package com.clinica.recetas.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @NotNull(message = "El id de la cita es obligatorio")
    private Integer idCita;

    @NotNull(message = "El id del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El id del médico es obligatorio")
    private Integer idMedico;

    @NotBlank(message = "El medicamento es obligatorio")
    private String medicamento;

    @NotBlank(message = "La indicación es obligatoria")
    private String indicaciones;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;

    @NotNull(message = "La duración del tratamiento es obligatoria")
    @Positive(message = "La duración debe ser mayor a 0")
    private Integer duracionDias;
}