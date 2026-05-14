package com.historial.clinico.repository;

import com.historial.clinico.model.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<HistorialClinico, Long> {

    List<HistorialClinico> findByIdPaciente(Long idPaciente);

    List<HistorialClinico> findByFechaConsulta(LocalDate fechaConsulta);

    List<HistorialClinico> findByDiagnosticoContainingIgnoreCase(String diagnostico);
}
