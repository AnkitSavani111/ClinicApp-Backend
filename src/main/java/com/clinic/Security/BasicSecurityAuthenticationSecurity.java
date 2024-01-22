package com.clinic.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicSecurityAuthenticationSecurity {

    // Filter Chain
    // Authenticate all requests
    // Basic Authentication
    // Disabling CSRF
    // Stateless Session(Rest API)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // authorizeHttpRequests - Enabled authontication for all requests
        http
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated())

        // httpBasic - Allows configuring HTTP Basic authentication. It asks for
        // username and password in the form of a popup alert.
        
                .httpBasic(Customizer.withDefaults())

        // Configuring Stateless Session
        
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // csrf - Cross-Site Request Forgery (CSRF) is an attack that forces an end user
        // to execute unwanted actions on a web application in which they're currently
        // authenticated.
        
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}