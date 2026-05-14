package com.historial.clinico.service;

import com.historial.clinico.model.HistorialClinico;
import com.historial.clinico.repository.HistorialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner inicializar(HistorialRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new HistorialClinico(null, 101L, LocalDate.of(2023, 5, 1), "Gripe común"));
                repository.save(new HistorialClinico(null, 102L, LocalDate.of(2023, 5, 2), "Hipertensión"));
                repository.save(new HistorialClinico(null, 103L, LocalDate.of(2023, 5, 3), "Diabetes tipo 2"));
            }
        };
    }
}
