package com.agora.dto.post;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.dto.user.UserSummaryDTO;

public record PostResponseDTO(
    UUID id,
    UserSummaryDTO author,
    String title,
    String description,
    OffsetDateTime createdAt
) {}