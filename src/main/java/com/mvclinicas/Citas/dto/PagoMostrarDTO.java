package com.mvclinicas.Citas.dto;

import java.time.LocalDateTime;
import lombok.*;

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
