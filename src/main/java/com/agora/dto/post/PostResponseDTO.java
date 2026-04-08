package com.agora.dto.post;

import java.time.LocalDate;
import java.util.UUID;

import com.agora.dto.user.UserSummaryDTO;

public record PostResponseDTO(
    UUID id,
    UserSummaryDTO author,
    String title,
    String description,
    LocalDate createdAt
) {}