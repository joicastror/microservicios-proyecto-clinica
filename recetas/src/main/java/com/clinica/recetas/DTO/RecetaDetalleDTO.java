package com.clinica.recetas.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaDetalleDTO {

    private Integer idReceta;

    private String nombrePaciente;

    private String nombreMedico;

    private String medicamento;

    private String indicaciones;
}