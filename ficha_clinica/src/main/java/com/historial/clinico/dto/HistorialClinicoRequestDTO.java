package com.historial.clinico.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HistorialClinicoRequestDTO {
    private Long idHistorial;

    @NotNull(message = "El ID del paciente no puede ser nulo")
    private Long idPaciente;

    @NotNull(message = "La fecha de consulta no puede ser nula")
    private LocalDate fechaConsulta;

    @NotBlank(message = "El diagnóstico no puede estar vacío")
    private String diagnostico;

    public HistorialClinicoRequestDTO() {}

    public HistorialClinicoRequestDTO(Long idHistorial, Long idPaciente, LocalDate fechaConsulta, String diagnostico) {
        this.idHistorial = idHistorial;
        this.idPaciente = idPaciente;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
    }

    // Getters y Setters
    public Long obtenerIdHistorial() {
        return idHistorial;
    }

    public void establecerIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Long obtenerIdPaciente() {
        return idPaciente;
    }

    public void establecerIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate obtenerFechaConsulta() {
        return fechaConsulta;
    }

    public void establecerFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String obtenerDiagnostico() {
        return diagnostico;
    }

    public void establecerDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}