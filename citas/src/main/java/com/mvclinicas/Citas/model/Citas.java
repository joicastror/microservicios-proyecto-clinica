package com.mvclinicas.Citas.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citas {



    @Id
    @GeneratedValue
    private Integer idCita;

    @NotNull(message = "El id del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El id del médico es obligatorio")
    private Integer idMedico;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha debe ser hoy o futura")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    
    private String estado;

    @NotBlank(message = "El motivo no puede estar vacío")
   
    private String motivo;

    private Integer idPago;

}
