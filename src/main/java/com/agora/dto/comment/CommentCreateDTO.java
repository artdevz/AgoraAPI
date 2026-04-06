package com.agora.dto.comment;

import java.util.UUID;

public record CommentCreateDTO(
    UUID postID,
    UUID userID,
    UUID parentID,
    String content
) {
}
