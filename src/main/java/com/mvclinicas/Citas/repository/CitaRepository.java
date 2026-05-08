package com.mvclinicas.Citas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mvclinicas.Citas.model.Citas;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Citas, Integer> {

    List<Citas> findByIdPaciente(Integer idPaciente);

    List<Citas> findByIdMedico(Integer idMedico);

    boolean existsByIdMedicoAndFechaAndHora(Integer idMedico, LocalDate fecha, LocalTime hora);
}