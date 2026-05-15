package com.clinica.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.recetas.model.Recetas;

public interface RecetaRepository extends JpaRepository<Recetas, Integer> {

    List<Recetas> findByIdPaciente(Integer idPaciente);

    List<Recetas> findByIdMedico(Integer idMedico);
}