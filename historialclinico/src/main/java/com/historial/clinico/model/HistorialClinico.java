package com.historial.clinico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @NotNull
    private Long idPaciente;

    @NotNull
    private LocalDate fechaConsulta;

    @NotBlank
    private String diagnostico;

    public HistorialClinico() {}

    public HistorialClinico(Long idHistorial, Long idPaciente, LocalDate fechaConsulta, String diagnostico) {
        this.idHistorial = idHistorial;
        this.idPaciente = Objects.requireNonNull(idPaciente, "ID paciente no puede ser nulo");
        this.fechaConsulta = Objects.requireNonNull(fechaConsulta, "Fecha de consulta no puede ser nula");
        this.diagnostico = Objects.requireNonNull(diagnostico, "Diagnóstico no puede ser nulo");
        if (diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnóstico no puede estar vacío");
        }
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
        this.idPaciente = Objects.requireNonNull(idPaciente, "ID paciente no puede ser nulo");
    }

    public LocalDate obtenerFechaConsulta() {
        return fechaConsulta;
    }

    public void establecerFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = Objects.requireNonNull(fechaConsulta, "Fecha de consulta no puede ser nula");
    }

    public String obtenerDiagnostico() {
        return diagnostico;
    }

    public void establecerDiagnostico(String diagnostico) {
        this.diagnostico = Objects.requireNonNull(diagnostico, "Diagnóstico no puede ser nulo");
        if (diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnóstico no puede estar vacío");
        }
        this.diagnostico = diagnostico;
    }
}
