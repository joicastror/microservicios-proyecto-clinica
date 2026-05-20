package com.soporte.clinica.controller;

import com.soporte.clinica.model.Citas;
import com.soporte.clinica.model.Soporte;
import com.soporte.clinica.service.SoporteService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/soporte")
public class SoporteController {

    private final SoporteService service;

    public SoporteController(SoporteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Soporte> listar() {
        return service.listar();
    }

    @PostMapping
    public Soporte crear(@Valid @RequestBody Soporte soporte) {
        return service.guardar(soporte);
    }

    @PutMapping("/{id}")
    public Soporte actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return service.actualizarEstado(id, estado);
    }

    @GetMapping("/cita/{id}")
    public Citas obtenerCita(@PathVariable Integer id) {
        return service.obtenerCita(id);
    }

    @GetMapping("/verificar-cita/{id}")
    public String verificarCita(@PathVariable Integer id) {
        return service.verificarCita(id);
    }

    @PostMapping("/ticket-correo")
    public String ticketCorreoNoAccede(@RequestParam String usuario, @RequestParam String email) {
        return service.crearTicketCorreoNoAccede(usuario, email);
    }

    @PostMapping("/ticket-resultados")
    public String ticketResultadosNoVisibles(
            @RequestParam String usuario,
            @RequestParam String email,
            @RequestParam(required = false) Integer idPaciente,
            @RequestParam(required = false) String problema
    ) {
        // problema opcional: NO_EN_VIOS, RESULTADO_INCORRECTO, RESULTADOS_TARDIOS, ERROR_VISUALIZACION, etc.
        return service.crearTicketResultadosNoVisibles(usuario, email, idPaciente, problema);
    }
}
