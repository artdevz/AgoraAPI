package com.agora.dto.user;

public record UserCreateDTO(
    String username,
    String email,
    String password
) {}
