package com.clinica.pagos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.pagos.dto.PagoCrearDTO;
import com.clinica.pagos.dto.PagoMostrarDTO;
import com.clinica.pagos.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<PagoMostrarDTO> obtenerTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public PagoMostrarDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<PagoMostrarDTO> crearPago(@RequestBody PagoCrearDTO dto) {
        return ResponseEntity.ok(service.registrarPago(dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Integer id) {
        PagoMostrarDTO pago = service.buscarPorId(id);
        
        if (pago != null) {
            service.eliminarPorId(id);
            return "Pago eliminado";
        } else {
            return "Pago no encontrado con id " + id;
        }
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarPago(@PathVariable Integer id, @RequestBody PagoCrearDTO dto) {
        PagoMostrarDTO actualizado = service.actualizarPago(id, dto);

        if (actualizado != null) {
            return "Pago actualizado";
        } else {
            return "Pago no encontrado con id " + id;
        }
    }
}