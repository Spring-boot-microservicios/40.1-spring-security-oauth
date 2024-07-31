package com.angelfg.app_security.dtos;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
