package com.example.biblioteca.configuration.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtUtils {
    
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.secret.expiration}")
    private String timeExpiration;
}
