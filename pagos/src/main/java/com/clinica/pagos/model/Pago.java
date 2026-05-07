package com.clinica.pagos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El ID de la cita es obligatorio")
    private Integer idCita;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser positivo")
    private Double monto;

    @NotBlank(message = "El estado debe ser obligatorio")
    private String estado; 

    @NotBlank(message = "El metodo de pago debe ser obligatorio")
    private String metodoPago;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}