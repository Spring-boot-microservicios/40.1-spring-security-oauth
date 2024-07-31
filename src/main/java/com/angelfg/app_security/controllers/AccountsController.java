package com.angelfg.app_security.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {

    // @PostAuthorize() // Hace algo despues de la autorizacion
    @PreAuthorize("hasAnyAuthority('VIEW_ACCOUNT', 'VIEW_CARDS')") // Hace algo antes de la autorizacion
    @GetMapping
    public Map<String, String> accounts() {
        //... business logic
        return Collections.singletonMap("message", "accounts");
    }
}
