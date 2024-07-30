package com.angelfg.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth ->
//            auth.anyRequest().permitAll()
//        )
//        .formLogin(Customizer.withDefaults())
//        .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    // 3 - Protege "/loans", "/balance", "/accounts", "/cards" excepto los demas
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/loans", "/balance", "/accounts", "/cards").authenticated()
                // .requestMatchers("/welcome", "/about").permitAll() => permite rutas especificas
                .anyRequest().permitAll()
        )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /*** -- SE GENERO PARA HACER USUARIOS EN MEMORIA -- ***/
    /*
    // 4 - Usuarios en memoria
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails admin = User
                .withUsername("admin")
                .password("to_be_encoded") // password debe ser codificado
                .authorities("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("to_be_encoded") // password debe ser codificado
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    */

    // Para pruebas de spring security para no codificar la contraseña
    // No se debe de realizar
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    // Uso de JDBC con ayuda del JPA para cargar el dataSource de spring
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    // 5 - Generando el password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
