package com.clinica.autenticacion.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Seguridad {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Desactivar seguridad automatica
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
            csrf(csrf -> csrf.disable())        //Desactiva permisos CSRF para facilitar grabados en Postman
            .authorizeHttpRequests(auth -> auth //Configura permisos para las solicitudes HTTP
                .anyRequest().permitAll()       //Permite acceso a TODOS los endpoints
            );
        return http.build();                    //Retorna la configuración de seguridad construida
    }
}