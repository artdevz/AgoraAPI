package com.agora.dto.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String username,
    String email,
    LocalDate createdAt
) {}
