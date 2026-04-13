package com.agora.mappers;

import com.agora.dto.comment.CommentResponseDTO;
import com.agora.dto.post.PostSummaryDTO;
import com.agora.dto.user.UserSummaryDTO;
import com.agora.entities.CommentEntity;
import com.agora.models.Comment;

public class CommentMapper {
    
    // DB -> API
    public static Comment ToDomain(CommentEntity entity) {
        if (entity == null) return null;

        Comment comment = new Comment(
            entity.getId(),
            PostMapper.ToDomain(entity.getPost()),
            UserMapper.ToDomain(entity.getAuthor()),
            entity.getCreatedAt(),
            entity.getContent(),
            entity.getStatus(),
            entity.getParent() != null ? ToDomain(entity.getParent()) : null
        );

        return comment;
    }

    // API -> DB
    public static CommentEntity ToEntity(Comment domain) {
        if (domain == null) return null;
        
        CommentEntity entity = new CommentEntity();
        entity.setId(domain.GetID());
        entity.setPost(PostMapper.ToEntity(domain.GetPost()));
        entity.setAuthor(UserMapper.ToEntity(domain.GetAuthor()));
        entity.setCreatedAt(domain.GetCreatedAt());
        entity.setContent(domain.GetContent());
        entity.setStatus(domain.GetStatus());
        entity.setParent(domain.GetParent() != null ? ToEntity(domain.GetParent()) : null);

        return entity;
    }

    // API -> OUT
    public static CommentResponseDTO ToResponseDTO(Comment domain) {
        if (domain == null) return null;

        return new CommentResponseDTO(
            domain.GetID(),
            new PostSummaryDTO(
                domain.GetPost().GetID(),
                domain.GetPost().GetTitle()
            ),
            new UserSummaryDTO(
                domain.GetAuthor().GetID(),
                domain.GetAuthor().GetNickname()
            ),
            domain.GetCreatedAt(),
            domain.GetContent(),
            domain.GetStatus(),
            domain.GetParent() != null ? domain.GetParent().GetID() : null
        );
    }

}
