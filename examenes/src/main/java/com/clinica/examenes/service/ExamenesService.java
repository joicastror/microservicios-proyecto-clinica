package com.clinica.examenes.service;

import org.springframework.stereotype.Service;

import com.clinica.examenes.model.Examenes;
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



   





}
