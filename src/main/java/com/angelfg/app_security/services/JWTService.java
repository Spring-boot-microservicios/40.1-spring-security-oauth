package com.angelfg.app_security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

// 2 - Generamos el comportamiento del JWT
@Service
public class JWTService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String JWT_SECRET= "jxgEQe.XHuPq8VdbyYFNkAN.dudQ0903YUn4";

    // 2.1 - Configura los claims del token
    private Claims getAllClaimsFromToken(String token) {
        // Descifra nuestro JWT
        final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    // 2.2 - Obtiene los claims del token
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 2.3 - Obtiene la expiracion del token
    private Date getExpirationDateFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    // 2.4 - Verificar si el token esta expirado
    private Boolean isTokenExpired(String token) {
        final Date expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.before(new Date()); // si es antes de la fecha actual
    }

    // 2.5 - Obtiene el username del token
    public String getUsernameFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    // 2.6 - Valida el token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String usernameFromUserDetails = userDetails.getUsername();
        final String usernameFromJWT = this.getUsernameFromToken(token);

        // Comparamos si el usuario y el claim subject del token sea igual al usuario
        // Y
        // Tambien verificamos si el token no esta expirado
        return (usernameFromUserDetails.equals(usernameFromJWT)) && !this.isTokenExpired(token);
    }

    // 2.7 - Generamos el token
    private String getToken(Map<String, Object> claims, String subject) {
        final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject) // username
            .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha actual en milliseconds
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // Fecha de expiracion del token
            .signWith(key)
            .compact();
    }

    // 2.8 - Obtenemos el token con sus claims
    public String generateToken(UserDetails userDetails) {
        // Aqui añadimos nuestros propios claims al token
        final Map<String, Object> claims = Collections.singletonMap("ROLES", userDetails.getAuthorities().toString());

        return this.getToken(claims, userDetails.getUsername());
    }

}
