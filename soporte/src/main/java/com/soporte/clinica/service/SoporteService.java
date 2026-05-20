package com.soporte.clinica.service;

import com.soporte.clinica.model.Citas;
import com.soporte.clinica.model.Soporte;
import com.soporte.clinica.repository.SoporteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SoporteService {

    private final SoporteRepository repository;

    public SoporteService(SoporteRepository repository) {
        this.repository = repository;
    }

    public List<Soporte> listar() {
        return repository.findAll();
    }

    public Soporte guardar(Soporte soporte) {
        soporte.setEstado("Abierto");
        return repository.save(soporte);
    }

    public Soporte actualizarEstado(Long id, String estado) {
        Soporte ticket = repository.findById(id).orElseThrow();
        ticket.setEstado(estado);
        return repository.save(ticket);
    }

    private void crearTicket(String usuario, String modulo, String descripcion) {
        Soporte soporte = new Soporte();
        soporte.setUsuario(usuario);
        soporte.setModulo(modulo);
        soporte.setDescripcion(descripcion);
        soporte.setEstado("Abierto");
        repository.save(soporte);
    }

    public Citas obtenerCita(Integer id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8081/citas/" + id;

            return restTemplate.getForObject(url, Citas.class);
        } catch (Exception e) {
            crearTicket("Sistema", "CITAS",
                    "Error al buscar la cita con ID: " + id);

            return null;
        }
    }

    public String verificarCita(Integer id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8081/citas/" + id;

            Citas cita = restTemplate.getForObject(url, Citas.class);

            // Si el microservicio responde 200 pero llega contenido null, creamos ticket igual.
            if (cita == null) {
                crearTicket("Sistema", "CITAS",
                        "No se encontró la cita con ID: " + id);
                return "Ticket de soporte de citas creado (cita no encontrada)";
            }

            return "Cita encontrada correctamente";
        } catch (Exception e) {
            crearTicket("Sistema", "CITAS",
                    "Error al buscar la cita con ID: " + id);

            return "Hubo un error en el microservicio de citas (ticket creado)";
        }
    }

    public String verificarCorreo(String usuario, String email) {
        try {
            crearTicket(usuario, "CORREO",
                    "El usuario no puede acceder al correo: " + email);

            return "Ticket de soporte de correo creado correctamente";
        } catch (Exception e) {
            crearTicket("Sistema", "CORREO",
                    "Error al crear soporte de correo para: " + email);

            return "Hubo un error creando el soporte de correo";
        }
    }

    public String verificarResultados(String usuario, String email, Integer idPaciente) {
        try {
            String detalle = "El paciente no puede ver sus resultados/exámenes. Email: " + email;
            if (idPaciente != null) {
                detalle += ". idPaciente: " + idPaciente;
            }

            crearTicket(usuario, "RESULTADOS", detalle);

            return "Ticket de soporte de resultados creado correctamente";
        } catch (Exception e) {
            crearTicket("Sistema", "RESULTADOS",
                    "Error al crear soporte de resultados para: " + email);

            return "Hubo un error creando el soporte de resultados";
        }
    }

    public String crearTicketCorreoNoAccede(String usuario, String email) {
        return verificarCorreo(usuario, email);
    }

    public String crearTicketResultadosNoVisibles(String usuario, String email, Integer idPaciente, String problema) {
        return verificarResultados(usuario, email, idPaciente, problema);
    }

    public String verificarResultados(String usuario, String email, Integer idPaciente, String problema) {
        try {
            String detalle;

            if (problema == null || problema.trim().isEmpty()) {
                detalle = "El paciente no puede ver sus resultados/exámenes. Email: " + email;
            } else {
                detalle = "Problema reportado de resultados/exámenes: " + problema + ". Email: " + email;
            }

            if (idPaciente != null) {
                detalle += ". idPaciente: " + idPaciente;
            }

            crearTicket(usuario, "RESULTADOS", detalle);

            return "Ticket de soporte de resultados creado correctamente";
        } catch (Exception e) {
            crearTicket("Sistema", "RESULTADOS",
                    "Error al crear soporte de resultados para: " + email);

            return "Hubo un error creando el soporte de resultados";
        }
    }
}
