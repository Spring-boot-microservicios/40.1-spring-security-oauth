package com.angelfg.app_security.services;

import com.angelfg.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JWTUserDetailServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    // 1 - Primera implementacion para JWT con UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.customerRepository.findByEmail(username)
            .map(customer -> {
                final List<SimpleGrantedAuthority> authorities = customer.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList();

                return new User(customer.getEmail(), customer.getPassword(), authorities);
            }).orElseThrow(() -> new UsernameNotFoundException("User not exist"));
    }

}
