package com.agora.security;

// import java.security.Key;
// import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    private static final String SECRET = "SuperSecretKeyForJwtGenerationSuperSecretKeyForJwtGeneration";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    private SecretKey GetSignKey() {
        /*byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);*/
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String GenerateToken(CustomUserDetails user) {
        return Jwts.builder()
            .subject(user.getId().toString())
            .claim("nickname", user.getNickname())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(GetSignKey())
            .compact();
    }

    public boolean ValidateToken(String token, CustomUserDetails user) {
        Claims claims = ExtractClaim(token);
        return claims.getSubject().equals(user.getId().toString()) && claims.getExpiration().after(new Date());
    }

    public UUID GetUserID(String token) {
        return UUID.fromString(ExtractClaim(token).getSubject());
    }

    public String GetNickname(String token) {
        return ExtractClaim(token).get("nickname", String.class);
    }

    private Claims ExtractClaim(String token) {
        return Jwts.parser()
            .verifyWith(GetSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}
