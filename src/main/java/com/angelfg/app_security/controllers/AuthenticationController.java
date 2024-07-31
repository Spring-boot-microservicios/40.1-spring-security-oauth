package com.angelfg.app_security.controllers;

import com.angelfg.app_security.dtos.JWTRequest;
import com.angelfg.app_security.dtos.JWTResponse;
import com.angelfg.app_security.services.JWTService;
import com.angelfg.app_security.services.JWTUserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 4 - Controller para generar nuestro login y obtener nuestro Jwt
@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUserDetailServiceImpl jwtUserDetailService;
    private final JWTService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request) {
        this.authenticate(request);

        final UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final String token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    // Metodo privado para realizar la autenticacion tipo basic
    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException | DisabledException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
