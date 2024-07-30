package com.angelfg.app_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableWebMvcSecurity => ya no es necesaria
public class AppSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSecurityApplication.class, args);
	}

}
