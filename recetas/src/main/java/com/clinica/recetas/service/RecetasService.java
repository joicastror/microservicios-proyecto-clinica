package com.clinica.recetas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clinica.recetas.DTO.RecetaCrearDTO;
import com.clinica.recetas.DTO.RecetaDetalleDTO;
import com.clinica.recetas.DTO.RecetaMostrarDTO;
import com.clinica.recetas.DTO.UsuarioDTO;
import com.clinica.recetas.model.Recetas;
import com.clinica.recetas.repository.RecetaRepository;

@Service
public class RecetasService {

    @Autowired
    private RecetaRepository repository;

  
    public RecetaMostrarDTO crearReceta(RecetaCrearDTO dto) {

       
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/autenticacion/buscar/"
                + dto.getIdMedico();

        UsuarioDTO usuario = restTemplate.getForObject(
                url,
                UsuarioDTO.class
        );

      
        if (usuario == null || !usuario.getRol().equalsIgnoreCase("MEDICO")) {
            throw new RuntimeException("El usuario no es un médico");
        }

       
        Recetas receta = Recetas.builder()
                .idCita(dto.getIdCita())
                .idPaciente(dto.getIdPaciente())
                .idMedico(dto.getIdMedico())
                .medicamento(dto.getMedicamento())
                .indicaciones(dto.getIndicaciones())
                .fechaEmision(dto.getFechaEmision())
                .duracionDias(dto.getDuracionDias())
                .build();

        Recetas guardada = repository.save(receta);

        return convertirDTO(guardada);
    }

   
    public List<RecetaMostrarDTO> listarTodas() {

        return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

  
    public RecetaMostrarDTO buscarPorId(Integer id) {

        Recetas receta = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Receta no encontrada"));

        return convertirDTO(receta);
    }

   
    public List<RecetaMostrarDTO> listarPorPaciente(Integer idPaciente) {

        return repository.findByIdPaciente(idPaciente)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

  
    public List<RecetaMostrarDTO> listarPorMedico(Integer idMedico) {

        return repository.findByIdMedico(idMedico)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }


    public void eliminarPorId(Integer id) {
        repository.deleteById(id);
    }

    
    public boolean existePorId(Integer id) {
        return repository.existsById(id);
    }

   
    public RecetaMostrarDTO actualizarReceta(
            Integer id,
            RecetaCrearDTO dto) {

        if (!repository.existsById(id)) {
            return null;
        }

        Recetas receta = repository.findById(id).get();

        receta.setIdCita(dto.getIdCita());
        receta.setIdPaciente(dto.getIdPaciente());
        receta.setIdMedico(dto.getIdMedico());
        receta.setMedicamento(dto.getMedicamento());
        receta.setIndicaciones(dto.getIndicaciones());
        receta.setFechaEmision(dto.getFechaEmision());
        receta.setDuracionDias(dto.getDuracionDias());

        Recetas actualizada = repository.save(receta);

        return convertirDTO(actualizada);
    }

    private RecetaMostrarDTO convertirDTO(Recetas receta) {

        return RecetaMostrarDTO.builder()
                .idReceta(receta.getIdReceta())
                .idCita(receta.getIdCita())
                .idPaciente(receta.getIdPaciente())
                .idMedico(receta.getIdMedico())
                .medicamento(receta.getMedicamento())
                .indicaciones(receta.getIndicaciones())
                .fechaEmision(receta.getFechaEmision())
                .duracionDias(receta.getDuracionDias())
                .build();
    }


    public List<RecetaDetalleDTO> listarDetallePaciente(Integer idPaciente) {

    List<Recetas> recetas = repository.findByIdPaciente(idPaciente);

    RestTemplate restTemplate = new RestTemplate();

    String urlPaciente =
            "http://localhost:8081/autenticacion/buscar/" + idPaciente;

    UsuarioDTO paciente = restTemplate.getForObject(
            urlPaciente,
            UsuarioDTO.class
    );

    return recetas.stream().map(receta -> {

        String urlMedico =
                "http://localhost:8081/autenticacion/buscar/"
                        + receta.getIdMedico();

        UsuarioDTO medico = restTemplate.getForObject(
                urlMedico,
                UsuarioDTO.class
        );

        return RecetaDetalleDTO.builder()
                .idReceta(receta.getIdReceta())
                .nombrePaciente(paciente.getNombre())
                .nombreMedico(medico.getNombre())
                .medicamento(receta.getMedicamento())
                .indicaciones(receta.getIndicaciones())
                .build();

    }).toList();
}
}