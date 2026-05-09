package com.clinica.examenes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.examenes.model.Examenes;
import com.clinica.examenes.service.ExamenesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/examenes")
public class ExamenesController {

    private final ExamenesService service;

    public ExamenesController(ExamenesService service){
        this.service = service;
    }



    @PostMapping("/guardar")
    public ResponseEntity<Examenes> guardarExamenes(@Valid @RequestBody Examenes examenes){
        Examenes nueva = service.guardarExamenes(examenes);
        return ResponseEntity.status(201).body(nueva);
    }

   



}
