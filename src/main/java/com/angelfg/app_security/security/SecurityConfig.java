package com.angelfg.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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

        // CSRF
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.authorizeHttpRequests(auth ->
            // auth.requestMatchers("/loans", "/balance", "/accounts", "/cards").authenticated()
            // .requestMatchers("/welcome", "/about").permitAll() => permite rutas especificas

                // Privilegios
                auth.requestMatchers("/loans").hasAuthority("VIEW_LOANS")
                    .requestMatchers("/balance").hasAuthority("VIEW_BALANCE")
                    .requestMatchers("/cards").hasAuthority("VIEW_CARDS")
                    .requestMatchers("/accounts").hasAnyAuthority("VIEW_ACCOUNT", "VIEW_CARDS")

                .anyRequest().permitAll()
        )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

        // Deshabilitar para no tener problemas en el navegador
//        http.cors(AbstractHttpConfigurer::disable);
//        http.csrf(AbstractHttpConfigurer::disable);

        // Configuracion personalizada de CORS
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // Configuracion sobre CSRF
        http.csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers("/welcome", "/about") // podemos ignorar los endpoints
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ).addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class); // se puede poner en constructor o instanciado

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
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // Uso de JDBC con ayuda del JPA para cargar el dataSource de spring
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    // 5 - Generando el password encoder
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir dominios
        // corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:8080"));
        corsConfiguration.setAllowedOrigins(List.of("*")); // Cualquier dominio esta permitido

        // Permitir los tipos de request
        // corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH"));
        corsConfiguration.setAllowedMethods(List.of("*")); // Permite todos los request

        corsConfiguration.setAllowedHeaders(List.of("*")); // Permite todos los headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Protege toda mi aplicacion
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
