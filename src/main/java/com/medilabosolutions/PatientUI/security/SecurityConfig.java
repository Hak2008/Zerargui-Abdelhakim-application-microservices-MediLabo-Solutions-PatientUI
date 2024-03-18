package com.medilabosolutions.PatientUI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // URL authorization configuration
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                // CSRF(Cross-Site Request Forgery) protection disabled
                .csrf(csrf -> csrf.disable());

        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails organizer = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .build();
        return new InMemoryUserDetailsManager(organizer);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}