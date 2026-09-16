package com.ecommerce.auth.infraestructure.entry_points;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (required for POST/PUT/DELETE requests in REST APIs)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Disable default form login & HTTP basic popups
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 3. Make session management STATELESS (no JSESSIONID cookies)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 4. Permit all requests under your base API route
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/ecommerce/user/**").permitAll()
                        .anyRequest().permitAll() // Temporarily permit all while building/testing
                );

        return http.build();
    }
}