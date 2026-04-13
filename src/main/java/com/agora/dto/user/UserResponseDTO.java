package com.agora.dto.user;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.enums.UserStatus;

public record UserResponseDTO(
    UUID id,
    String nickname,
    String email,
    OffsetDateTime createdAt,
    UserStatus status
) {}
