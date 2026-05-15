package com.clinica.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.notificaciones.model.Notificacion;
import com.clinica.notificaciones.repository.NotificacionRepository;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    //Metodo principal
    public Notificacion registrarYEnviar(Notificacion notificacion) {
        // 1. Lógica de "Envío" (Simulada por consola)
        System.out.println("======= SIMULACIÓN DE ENVÍO DE NOTIFICACIÓN =======");
        System.out.println("TIPO: " + notificacion.getTipo());
        System.out.println("DESTINATARIO: " + notificacion.getDestinatario());
        System.out.println("MENSAJE: " + notificacion.getMensaje());
        System.out.println("====================================================");

        // 2. Seteamos el estado a ENVIADA antes de guardar
        notificacion.setEstado("ENVIADA");

        // 3. Guardamos en la base de datos de Notificaciones para el historial
        return repository.save(notificacion);
    }

    // Por si quieres ver el historial desde el Controller
    public List<Notificacion> listarHistorial() {
        return repository.findAll();
    }
}
