package com.agora.dto.user;

import com.agora.enums.AuthProvider;

public record UserCreateDTO(
    String nickname,
    String email,
    String password,
    AuthProvider provider
) {}
