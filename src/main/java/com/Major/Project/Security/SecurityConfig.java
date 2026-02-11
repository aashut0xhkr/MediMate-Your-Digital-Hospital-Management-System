package com.Major.Project.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/HMS/Appointment/**").hasAnyRole("DOCTOR", "PATIENT")
                .requestMatchers("/HMS/Patient/**").hasAnyRole("PATIENT", "DOCTOR")
                .requestMatchers("/HMS/Medicine/**").hasAnyRole("DOCTOR")
                .requestMatchers("/HMS/Staff/**").hasAnyRole("DOCTOR", "ADMIN")
                .requestMatchers("/HMS/LabTest/**").hasAnyRole("DOCTOR", "PATIENT")
                .requestMatchers("/HMS/Inventory/**").hasAnyRole("DOCTOR", "ADMIN")
                .requestMatchers("/HMS/Bill/**").hasAnyRole("DOCTOR", "PATIENT")
                .requestMatchers("/HMS/Doctor/**").hasAnyRole("DOCTOR")
                .anyRequest().authenticated()                
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exception -> exception
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{ \"error\": \"Forbidden\", \"message\": \"Access Denied: You do not have the required permissions.\" }");
            })
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
}   
}
