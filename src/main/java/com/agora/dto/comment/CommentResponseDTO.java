package com.agora.dto.comment;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.agora.dto.post.PostSummaryDTO;
import com.agora.dto.user.UserSummaryDTO;
import com.agora.enums.SubmitStatus;

public record CommentResponseDTO(
    UUID id,
    PostSummaryDTO post,
    UserSummaryDTO author,
    OffsetDateTime createdAt,
    String content,
    SubmitStatus status,
    List<CommentResponseDTO> replies
) {}
