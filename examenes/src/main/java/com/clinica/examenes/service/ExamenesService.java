package com.clinica.examenes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clinica.examenes.dto.ExamenDetalleDTO;
import com.clinica.examenes.model.Examenes;
import com.clinica.examenes.model.Usuario;
import com.clinica.examenes.repository.ExamenesRepository;

@Service

public class ExamenesService {


    private final ExamenesRepository repository;

    public ExamenesService( ExamenesRepository repository){
        this.repository= repository;

    }



    public Examenes guardarExamenes(Examenes examenes){
        return repository.save(examenes);
         
    }

    public List<Examenes> buscarPorPaciente(Integer idPaciente) {
        return repository.findByIdPaciente(idPaciente);
    }

    public List<ExamenDetalleDTO> obtenerExamenDTO(Integer idPaciente) {
        List<Examenes> examenes = repository.findByIdPaciente(idPaciente);
        List<ExamenDetalleDTO> examenesDTO = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (Examenes e : examenes) {
            ExamenDetalleDTO dto = new ExamenDetalleDTO();
            dto.setId(e.getId());
            dto.setNombreExamen(e.getNombreExamen());
            dto.setFechaExamen(e.getFechaExamen());
            dto.setResultado(e.getResultado());
            dto.setEstado(e.getEstado());

            /*
            //El del teacher enseñado:
            String urlPaciente = "http://localhost:8081/autenticacion/buscar/" + e.getIdPaciente();
            Usuario paciente = restTemplate.getForObject(urlPaciente, Usuario.class);
            if (paciente != null) {
                dto.setNombrePaciente(paciente.getNombre());
            }

            String urlMedico = "http://localhost:8081/autenticacion/buscar/" + e.getIdMedico();
            Usuario medico = restTemplate.getForObject(urlMedico, Usuario.class);
            if (medico != null) {
                dto.setNombreMedico(medico.getNombre());
            */
            
            //Los Exception ex de abajo son por ejemplo si es que no encuentra la id o si el micro hizo boom
            String urlPaciente = "http://localhost:8081/autenticacion/buscar/" + e.getIdPaciente();
            try {
                Usuario pac = restTemplate.getForObject(urlPaciente, Usuario.class);
                
                if (pac != null && pac.getNombre() != null) {
                    dto.setNombrePaciente(pac.getNombre());
                } else {
                    dto.setNombrePaciente("Paciente encontrado pero sin nombre");
                }
            } catch (Exception ex) {
                dto.setNombrePaciente("Error: No se pudo conectar con Autenticación");
            }

            String urlMedico = "http://localhost:8081/autenticacion/buscar/" + e.getIdMedico();
            try {
                Usuario pac = restTemplate.getForObject(urlMedico, Usuario.class);
                
                if (pac != null && pac.getNombre() != null) {
                    dto.setNombreMedico(pac.getNombre());
                } else {
                    dto.setNombreMedico("Medico encontrado pero sin nombre");
                }
            } catch (Exception ex) {
                dto.setNombreMedico("Error: No se pudo conectar con Autenticación");
            }
            examenesDTO.add(dto);
        }
        return examenesDTO;
    }

    public ExamenDetalleDTO obtenerExamenPorId(Integer id) {
    Examenes e = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Examen no encontrado"));

        ExamenDetalleDTO dto = new ExamenDetalleDTO();
        dto.setId(e.getId());
        dto.setNombreExamen(e.getNombreExamen());
        dto.setFechaExamen(e.getFechaExamen());
        dto.setResultado(e.getResultado());
        dto.setEstado(e.getEstado());

        RestTemplate restTemplate = new RestTemplate();
        try {
            String urlPac = "http://localhost:8081/autenticacion/buscar/" + e.getIdPaciente();
            Usuario pac = restTemplate.getForObject(urlPac, Usuario.class);
            dto.setNombrePaciente(pac != null ? pac.getNombre() : "No encontrado");
        } catch (Exception ex) {
            dto.setNombrePaciente("Error de conexión");
        }
        try {
            String urlMed = "http://localhost:8081/autenticacion/buscar/" + e.getIdMedico();
            Usuario med = restTemplate.getForObject(urlMed, Usuario.class);
            dto.setNombreMedico(med != null ? med.getNombre() : "No encontrado");
        } catch (Exception ex) {
            dto.setNombreMedico("Error de conexión");
        }

        return dto;
    }
}
