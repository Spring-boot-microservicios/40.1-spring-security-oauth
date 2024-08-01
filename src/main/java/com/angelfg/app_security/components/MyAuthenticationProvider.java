package com.angelfg.app_security.components;

import com.angelfg.app_security.entities.CustomerEntity;
import com.angelfg.app_security.entities.RoleEntity;
import com.angelfg.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Ya no dependemos de AuthenticationProvider sino de JWT
@Component
@AllArgsConstructor
public class MyAuthenticationProvider {} /*implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    // No es muy comun utilizar el Authentication providers, usar mejor el UserDetails => CustomerUserDetailsServiceImpl.class
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString(); // puede ser un token, en este caso es un string

        CustomerEntity customerFromDB = this.customerRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid Credentials"));

        final String passwordFromDB = customerFromDB.getPassword();

        if (passwordEncoder.matches(password, passwordFromDB)) {
//            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(customerFromDB.getRole()));

            List<RoleEntity> roles = customerFromDB.getRoles();
            List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid credentials - Password");
        }
    }

    // No lo utilizamos pero decimos cual autenticacion soportara
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
*/