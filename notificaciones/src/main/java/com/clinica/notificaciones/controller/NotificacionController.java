package com.clinica.notificaciones.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.notificaciones.model.Notificacion;
import com.clinica.notificaciones.service.NotificacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping("/enviar")
    public ResponseEntity<Notificacion> enviarNotificacion(@Valid @RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(service.registrarYEnviar(notificacion));
    }
}
