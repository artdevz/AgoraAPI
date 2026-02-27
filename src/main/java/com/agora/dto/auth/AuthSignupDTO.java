package com.agora.dto.auth;

public record AuthSignupDTO(
    String nickname,
    String email,
    String password
) {}
