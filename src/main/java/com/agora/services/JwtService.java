package com.agora.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agora.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String GenerateAccessToken(User user) {
        return Jwts.builder()
            .subject(user.GetID().toString())
            .claim("email", user.GetEmail())
            .claim("provider", user.GetProvider().name())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(GetKey())
            .compact();
    }

    private Key GetKey() { return Keys.hmacShaKeyFor(secret.getBytes()); }

}
