package com.clinica.recetas.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaMostrarDTO {

    private Integer idReceta;

    private Integer idCita;

    private Integer idPaciente;

    private Integer idMedico;

    private String medicamento;

    private String indicaciones;

    private LocalDate fechaEmision;

    private Integer duracionDias;
}