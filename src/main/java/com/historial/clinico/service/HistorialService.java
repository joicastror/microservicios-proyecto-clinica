package com.historial.clinico.service;

import com.historial.clinico.dto.HistorialClinicoDTO;
import com.historial.clinico.dto.HistorialClinicoRequestDTO;
import com.historial.clinico.model.HistorialClinico;
import com.historial.clinico.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    // URLs de microservicios (configurar cuando estén disponibles)
    private static final String URL_AUTENTICACION = "http://localhost:8081";
    private static final String URL_CITAS = "http://localhost:8082";
    private static final String URL_PAGOS = "http://localhost:8083";

    public List<HistorialClinicoDTO> listar() {
        return repository.findAll().stream()
                .map(h -> new HistorialClinicoDTO(h.obtenerIdHistorial(), h.obtenerIdPaciente(), h.obtenerFechaConsulta()))
                .collect(Collectors.toList());
    }

    public List<HistorialClinicoDTO> buscarPorIdPaciente(Long idPaciente) {
        return repository.findByIdPaciente(idPaciente).stream()
                .map(h -> new HistorialClinicoDTO(h.obtenerIdHistorial(), h.obtenerIdPaciente(), h.obtenerFechaConsulta()))
                .collect(Collectors.toList());
    }

    public List<HistorialClinicoDTO> buscarPorFechaConsulta(LocalDate fechaConsulta) {
        return repository.findByFechaConsulta(fechaConsulta).stream()
                .map(h -> new HistorialClinicoDTO(h.obtenerIdHistorial(), h.obtenerIdPaciente(), h.obtenerFechaConsulta()))
                .collect(Collectors.toList());
    }

    public List<HistorialClinicoDTO> buscarPorDiagnostico(String diagnostico) {
        return repository.findByDiagnosticoContainingIgnoreCase(diagnostico).stream()
                .map(h -> new HistorialClinicoDTO(h.obtenerIdHistorial(), h.obtenerIdPaciente(), h.obtenerFechaConsulta()))
                .collect(Collectors.toList());
    }

    public HistorialClinicoDTO guardarHistorial(HistorialClinicoRequestDTO request) {
        HistorialClinico historial = new HistorialClinico(null, request.obtenerIdPaciente(), request.obtenerFechaConsulta(), request.obtenerDiagnostico());
        HistorialClinico saved = repository.save(historial);
        return new HistorialClinicoDTO(saved.obtenerIdHistorial(), saved.obtenerIdPaciente(), saved.obtenerFechaConsulta());
    }

    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    public Optional<HistorialClinicoDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(h -> new HistorialClinicoDTO(h.obtenerIdHistorial(), h.obtenerIdPaciente(), h.obtenerFechaConsulta()));
    }

    public HistorialClinicoDTO actualizarHistorial(Long id, HistorialClinicoRequestDTO request) {
        HistorialClinico historial = new HistorialClinico(id, request.obtenerIdPaciente(), request.obtenerFechaConsulta(), request.obtenerDiagnostico());
        HistorialClinico updated = repository.save(historial);
        return new HistorialClinicoDTO(updated.obtenerIdHistorial(), updated.obtenerIdPaciente(), updated.obtenerFechaConsulta());
    }

    // Métodos de integración con otros microservicios

    /**
     * Obtiene información de citas asociadas a un paciente
     * Requiere que el microservicio de Citas esté disponible en URL_CITAS
     */
    public List<Object> obtenerCitasPorPaciente(Long idPaciente) {
        try {
            String url = URL_CITAS + "/api/citas/paciente/" + idPaciente;
            Object[] citas = restTemplate.getForObject(url, Object[].class);
            return citas != null ? List.of(citas) : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al obtener citas del microservicio: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Obtiene información de pagos asociados a un paciente
     * Requiere que el microservicio de Pagos esté disponible en URL_PAGOS
     */
    public List<Object> obtenerPagosPorPaciente(Long idPaciente) {
        try {
            String url = URL_PAGOS + "/api/pagos/paciente/" + idPaciente;
            Object[] pagos = restTemplate.getForObject(url, Object[].class);
            return pagos != null ? List.of(pagos) : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al obtener pagos del microservicio: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Verifica autenticación del usuario
     * Requiere que el microservicio de Autenticación esté disponible en URL_AUTENTICACION
     */
    public boolean verificarAutenticacion(String token) {
        try {
            String url = URL_AUTENTICACION + "/api/auth/verificar";
            Boolean resultado = restTemplate.postForObject(url, token, Boolean.class);
            return resultado != null && resultado;
        } catch (Exception e) {
            System.err.println("Error al verificar autenticación: " + e.getMessage());
            return false;
        }
    }
}
