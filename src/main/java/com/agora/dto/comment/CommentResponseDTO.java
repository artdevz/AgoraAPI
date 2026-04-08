package com.agora.dto.comment;

import java.time.LocalDate;
import java.util.UUID;

public record CommentResponseDTO(
    UUID id,
    UUID postID,
    UUID userID,
    LocalDate createdAt,
    String content,
    boolean edited,
    UUID parentID
) {}
