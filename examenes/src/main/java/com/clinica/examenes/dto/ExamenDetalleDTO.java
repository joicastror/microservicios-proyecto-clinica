package com.clinica.examenes.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamenDetalleDTO {
    private Integer id;
    private String nombreExamen;
    private LocalDate fechaExamen;
    private String nombrePaciente;
    private String nombreMedico;
    private String resultado;
    private String estado;
}
