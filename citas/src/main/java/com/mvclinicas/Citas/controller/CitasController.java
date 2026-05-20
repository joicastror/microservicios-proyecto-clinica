package com.mvclinicas.Citas.controller;

import com.mvclinicas.Citas.model.Citas;
import com.mvclinicas.Citas.service.CitasService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitasController {

    @Autowired
    private CitasService service;

    
    @PostMapping("/crear")
    public Citas crear(@Valid @RequestBody Citas cita) {
        return service.crearCita(cita);
    }

   
    @GetMapping("/{id}")
    public Citas obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    
    @GetMapping
    public List<Citas> listar() {
        return service.listar();
    }

    
    @GetMapping("/paciente/{idPaciente}")
    public List<Citas> listarPorPaciente(@PathVariable Integer idPaciente) {
        return service.listarPorPaciente(idPaciente);
    }

    
    @GetMapping("/medico/{idMedico}")
    public List<Citas> listarPorMedico(@PathVariable Integer idMedico) {
        return service.listarPorMedico(idMedico);
    }

    
    @PutMapping("/{id}/confirmar")
    public Citas confirmar(@PathVariable Integer id) {
        return service.confirmarCita(id);
    }

   
    @PutMapping("/{id}/cancelar")
    public Citas cancelar(@PathVariable Integer id) {
        return service.cancelarCita(id);
    }
}