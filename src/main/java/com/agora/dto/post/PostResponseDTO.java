package com.agora.dto.post;

import java.time.LocalDate;
import java.util.UUID;

public record PostResponseDTO(
    UUID id,
    String title,
    String description,
    LocalDate createdAt
) {}