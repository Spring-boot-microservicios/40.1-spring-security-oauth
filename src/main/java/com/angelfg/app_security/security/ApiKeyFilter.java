package com.angelfg.app_security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class ApiKeyFilter extends OncePerRequestFilter {

    // Variables de entorno las apiKeys
    private static final String API_KEY = "myKey";
    private static final String API_KEY_HEADER = "api_key";

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String path = request.getRequestURI();
            System.out.println("path ==> " + path);

            if (path.startsWith("/cards")) {
                System.out.println("Filtrando ruta segura: " + path);

                // Apikey en el Header
                String apiKey = Optional.of(request.getHeader(API_KEY_HEADER))
                        .orElseThrow(() -> new BadCredentialsException("No header api key"));

                if (!apiKey.equals(API_KEY)) {
                    throw new BadCredentialsException("Invalid api key - different");
                }
            }


        } catch (Exception e) {
            throw new BadCredentialsException("Invalid api key - error");
        }

        filterChain.doFilter(request, response);
    }

}
