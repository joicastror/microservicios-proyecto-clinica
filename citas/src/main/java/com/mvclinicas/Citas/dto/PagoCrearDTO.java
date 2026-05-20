package com.mvclinicas.Citas.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoCrearDTO {

    private Integer idCita;
    private Integer idPaciente;
    private Double monto;
    private String metodoPago;
}