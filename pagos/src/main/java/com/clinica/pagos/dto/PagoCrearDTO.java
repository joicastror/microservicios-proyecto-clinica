package com.clinica.pagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoCrearDTO {
    @NotNull(message = "El ID de la cita es requerido")
    private Integer idCita;

    @NotNull(message = "El ID del paciente es requerido")
    private Integer idPaciente;

    @NotNull(message = "El monto es requerido")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @NotBlank(message = "Debe especificar un método de pago")
    @Pattern(regexp = "TARJETA|EFECTIVO|TRANSFERENCIA", message = "Método de pago no válido")
    private String metodoPago;
}