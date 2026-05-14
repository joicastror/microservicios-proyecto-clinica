package com.clinica.autenticacion.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private LocalDateTime timestamp;        //Fecha y hora del error
    private int status;                     //Código de estado HTTP (ej: 400, 404, 500)
    private String mensaje;                 //Mensaje general del error
    private Map<String, String> errores;    //Detalle de errores por campo (ej: "nombre": el nombre no puede estar vacio)
    private String path;                    //Ruta del endpoint donde ocurrio el mensaje (ej: "/agregar")

    public ErrorDTO(LocalDateTime timestamp, int status, String mensaje, Map<String, String> errores, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensaje = mensaje;
        this.errores = errores;
        this.path = path;
    }

}
