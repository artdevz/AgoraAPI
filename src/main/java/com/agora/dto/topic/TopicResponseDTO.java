package com.agora.dto.topic;

import java.time.LocalDate;
import java.util.UUID;

public record TopicResponseDTO(
    UUID id,
    String title,
    String description,
    LocalDate createdAt
) {}