package com.angelfg.app_security.services;

//import com.angelfg.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerUserDetailsServiceImpl /*implements UserDetailsService*/ {

    // LA LOGICA DE NEGOCIOS AHORA LA DEJAMOS EN EL AUTHENTICATION_PROVIDERS
    /*
    private final CustomerRepository customerRepository;

    // Uso de UserDetailsService para realizar nuestra propia implementacion de seguridad
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.customerRepository.findByEmail(username)
                .map(customer -> {
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
                    return new User(customer.getEmail(), customer.getPassword(), authorities);
                }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    */

}
