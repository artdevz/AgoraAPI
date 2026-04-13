package com.agora.dto.post;

import java.util.UUID;

public record PostSummaryDTO(
    UUID id,
    String title
) {}
