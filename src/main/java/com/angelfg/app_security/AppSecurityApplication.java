package com.angelfg.app_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
// @EnableWebMvcSecurity => ya no es necesaria
// @EnableWebSecurity(debug = true) // activamos el debug del security (por default es false)
public class AppSecurityApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppSecurityApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Password encode: " + passwordEncoder.encode("to_be_encoded"));
//		Encriptacion: $2a$10$CFVmizYZkr5/66oCqoeULeur/9qGUBBSxOIlj/t8/Bq4SGJ9pod5.
//	}

}
