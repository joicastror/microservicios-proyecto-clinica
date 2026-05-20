package com.mvclinicas.Citas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mvclinicas.Citas.model.Citas;
import com.mvclinicas.Citas.repository.CitaRepository;
import com.mvclinicas.Citas.dto.PagoCrearDTO;
import com.mvclinicas.Citas.dto.PagoMostrarDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitasService {

    @Autowired
    private CitaRepository repository;

   
    //  CREAR CITA CON PAGO
    
    public Citas crearCita(Citas cita) {

        // Validar disponibilidad del médico
        boolean existe = repository.existsByIdMedicoAndFechaAndHora(
                cita.getIdMedico(),
                cita.getFecha(),
                cita.getHora()
        );

        if (existe) {
            throw new RuntimeException("El médico ya tiene una cita en ese horario");
        }

        // Validar fecha y hora actual
        if (cita.getFecha().isEqual(LocalDate.now()) &&
            cita.getHora().isBefore(LocalTime.now())) {
            throw new RuntimeException("No puedes agendar en una hora pasada");
        }

        // Estado inicial
        cita.setEstado("PENDIENTE");

        // Guardar cita
        Citas citaGuardada = repository.save(cita);

        
        //LLAMAR A MICROSERVICIO PAGOS
        
        try {
            RestTemplate restTemplate = new RestTemplate();

            PagoCrearDTO pagoDTO = PagoCrearDTO.builder()
                    .idCita(citaGuardada.getIdCita())
                    .idPaciente(citaGuardada.getIdPaciente())
                    .monto(20000.0) // puedes hacerlo dinámico
                    .metodoPago("TARJETA")
                    .build();

            String url = "http://localhost:8082/pagos/crear";

            PagoMostrarDTO pago = restTemplate.postForObject(
                    url,
                    pagoDTO,
                    PagoMostrarDTO.class
            );

            // Guardar idPago si todo sale bien
            if (pago != null) {
                citaGuardada.setIdPago(pago.getId());
                repository.save(citaGuardada);
            }

        } catch (Exception e) {
            // No detiene la cita, pero informa el error
            System.out.println("Error al conectar con pagos: " + e.getMessage());
        }

        return citaGuardada;
    }

    
    //  OBTENER POR ID
    
    public Citas obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    //  LISTAR TODAS
  
    public List<Citas> listar() {
        return repository.findAll();
    }

    
    //  LISTAR POR PACIENTE
    
    public List<Citas> listarPorPaciente(Integer idPaciente) {
        return repository.findByIdPaciente(idPaciente);
    }

    
    // lISTAR POR MÉDICO
    
    public List<Citas> listarPorMedico(Integer idMedico) {
        return repository.findByIdMedico(idMedico);
    }

    // CONFIRMAR CITA
    
    public Citas confirmarCita(Integer id) {
        Citas cita = obtenerPorId(id);
        cita.setEstado("CONFIRMADA");
        return repository.save(cita);
    }

    
    //  CANCELAR CITA
    
    public Citas cancelarCita(Integer id) {
        Citas cita = obtenerPorId(id);
        cita.setEstado("CANCELADA");
        return repository.save(cita);
    }
}

