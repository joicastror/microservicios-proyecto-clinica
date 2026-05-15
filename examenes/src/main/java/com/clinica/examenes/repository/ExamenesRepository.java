package com.clinica.examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.examenes.model.Examenes;

public interface ExamenesRepository extends JpaRepository<Examenes,Integer>{
    List<Examenes> findByIdPaciente(Integer idPaciente);
    

}
