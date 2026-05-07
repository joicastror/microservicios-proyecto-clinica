package com.clinica.pagos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoMostrarDTO {
    private Integer id;
    private Double monto;
    private String estado;
    private String metodoPago;
    private LocalDateTime fechaCreacion;
}
