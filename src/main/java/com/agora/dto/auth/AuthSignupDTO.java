package com.agora.dto.auth;

public record AuthSignupDTO(
    String username,
    String email,
    String password
) {}
