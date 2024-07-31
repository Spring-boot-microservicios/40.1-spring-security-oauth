package com.angelfg.app_security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

// Generamos un filtro para el Csrf
public class CsrfCookieFilter extends OncePerRequestFilter {

    // Genera el token y los setea a los header para que no ocurrar el CSRF
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (Objects.nonNull(csrfToken.getHeaderName())) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        filterChain.doFilter(request, response);
    }

}
