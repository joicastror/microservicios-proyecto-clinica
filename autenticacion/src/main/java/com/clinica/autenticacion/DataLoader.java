package com.clinica.autenticacion;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clinica.autenticacion.model.Usuario;
import com.clinica.autenticacion.repository.UsuarioRepository;

@Configuration
public class DataLoader implements CommandLineRunner{
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin General"); 
            admin.setRut("1-1");
            admin.setEmail("admin@clinica.cl");
            admin.setClave(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");
            admin.setFechaNacimiento(LocalDate.of(1990, 1, 1)); 
            admin.setEstado(true); 

            Usuario medico = new Usuario();
            medico.setNombre("Dr. Gregory House"); 
            medico.setRut("2-2");
            medico.setEmail("house@clinica.cl");
            medico.setClave(passwordEncoder.encode("diagnostico"));
            medico.setRol("MEDICO");
            medico.setEspecialidad("Diagnóstico");
            medico.setFechaNacimiento(LocalDate.of(1970, 5, 15));
            medico.setEstado(true);

            repository.save(admin);
            repository.save(medico);
        }
    }
}
