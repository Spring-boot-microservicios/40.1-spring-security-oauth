package com.angelfg.app_security.security;

import com.angelfg.app_security.services.JWTService;
import com.angelfg.app_security.services.JWTUserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

// 6 - JWT Generamos filtro del token
@Component
@AllArgsConstructor
@Slf4j
public class JWTValidationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    private final JWTService jwtService;
    private final JWTUserDetailServiceImpl jwtUserDetailService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String username = null;
        String jwt = null;

        // Obtenemos en el header el Authorization Bearer asdasdsadasd
        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);

        // Verificamos que el token tenga los estandares de Bearer
        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(AUTHORIZATION_HEADER_BEARER)) {
            jwt = requestTokenHeader.substring(7); // Cortamos el Bearer  para obtener el token

            try {
                username = jwtService.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                // Token invalido
                log.error(e.getMessage());
            } catch (ExpiredJwtException e) {
                // Token expirado
                log.warn(e.getMessage());
            }
        }

        // Verificamos que el username no sea null y el contexto sea de tipo authentication
        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            final UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

            // Verificamos si el token es valido con el usuario
            if (this.jwtService.validateToken(jwt, userDetails)) {

                // Creamos la autenticacion
                UsernamePasswordAuthenticationToken usernameAndPassAuthToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );

                // Versiones web y mobil debemos generar este details
                usernameAndPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Setear la autenticacion al contexto
                SecurityContextHolder.getContext().setAuthentication(usernameAndPassAuthToken);
            }

        }

        filterChain.doFilter(request, response);
    }

}
