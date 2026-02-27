package com.agora.dto.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String nickname,
    String email,
    LocalDate createdAt
) {}
