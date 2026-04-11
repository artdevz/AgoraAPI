package com.agora.dto.comment;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.dto.user.UserSummaryDTO;

public record CommentResponseDTO(
    UUID id,
    UUID postID,
    UserSummaryDTO author,
    OffsetDateTime createdAt,
    String content,
    boolean edited,
    boolean deleted,
    UUID parentID
) {}
