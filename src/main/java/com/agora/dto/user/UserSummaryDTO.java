package com.agora.dto.user;

import java.util.UUID;

public record UserSummaryDTO(
    UUID id,
    String nickname
) {}
