package com.historial.clinico.dto;

import java.time.LocalDate;

public class HistorialClinicoDTO {
    private Long idHistorial;
    private Long idPaciente;
    private LocalDate fechaConsulta;

    public HistorialClinicoDTO() {}

    public HistorialClinicoDTO(Long idHistorial, Long idPaciente, LocalDate fechaConsulta) {
        this.idHistorial = idHistorial;
        this.idPaciente = idPaciente;
        this.fechaConsulta = fechaConsulta;
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
}