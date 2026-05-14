package com.historial.clinico.controller;

import com.historial.clinico.dto.HistorialClinicoDTO;
import com.historial.clinico.dto.HistorialClinicoRequestDTO;
import com.historial.clinico.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiales")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<HistorialClinicoDTO> listar() {
        return historialService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinicoDTO> buscarPorId(@PathVariable Long id) {
        Optional<HistorialClinicoDTO> historial = historialService.buscarPorId(id);
        return historial.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{idPaciente}")
    public List<HistorialClinicoDTO> buscarPorIdPaciente(@PathVariable Long idPaciente) {
        return historialService.buscarPorIdPaciente(idPaciente);
    }

    @GetMapping("/fecha/{fechaConsulta}")
    public List<HistorialClinicoDTO> buscarPorFechaConsulta(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaConsulta) {
        return historialService.buscarPorFechaConsulta(fechaConsulta);
    }

    @GetMapping("/diagnostico/{diagnostico}")
    public List<HistorialClinicoDTO> buscarPorDiagnostico(@PathVariable String diagnostico) {
        return historialService.buscarPorDiagnostico(diagnostico);
    }

    @PostMapping
    public ResponseEntity<HistorialClinicoDTO> guardarHistorial(@Valid @RequestBody HistorialClinicoRequestDTO request) {
        HistorialClinicoDTO historial = historialService.guardarHistorial(request);
        return ResponseEntity.status(201).body(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinicoDTO> actualizarHistorial(@PathVariable Long id, @Valid @RequestBody HistorialClinicoRequestDTO request) {
        HistorialClinicoDTO historial = historialService.actualizarHistorial(id, request);
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        historialService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
