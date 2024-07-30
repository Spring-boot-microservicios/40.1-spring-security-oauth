package com.angelfg.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1 - Configuracion default
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth ->
//            auth.anyRequest().authenticated()
//        )
//        .formLogin(Customizer.withDefaults())
//        .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    // 2 - Cualquier request permitir sin autenticacion
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
