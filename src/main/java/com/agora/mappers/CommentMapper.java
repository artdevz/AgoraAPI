package com.agora.mappers;

import com.agora.dto.comment.CommentResponseDTO;
import com.agora.entities.CommentEntity;
import com.agora.models.Comment;

public class CommentMapper {
    
    public static Comment toDomain(CommentEntity entity) {
        if (entity == null) return null;

        Comment comment = new Comment(
            entity.getId(),
            PostMapper.toDomain(entity.getPost(), false),
            UserMapper.toDomain(entity.getUser(), false),
            entity.getCreatedAt(),
            entity.getContent(),
            entity.isEdited(),
            entity.getParent() != null ? toDomain(entity.getParent()) : null
        );

        return comment;
    }

    public static CommentEntity toEntity(Comment comment) {
        if (comment == null) return null;
        
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.GetID());
        entity.setPost(PostMapper.toEntity(comment.GetPost()));
        entity.setUser(UserMapper.toEntity(comment.GetUser()));
        entity.setCreatedAt(comment.GetCreatedAt());
        entity.setContent(comment.GetContent());
        entity.setEdited(comment.IsEdited());
        entity.setParent(comment.GetParent() != null ? toEntity(comment.GetParent()) : null);

        return entity;
    }

    public static CommentResponseDTO toResponseDTO(Comment comment) {
        if (comment == null) return null;

        return new CommentResponseDTO(
            comment.GetID(),
            comment.GetPost().GetID(),
            comment.GetUser().GetID(),
            comment.GetCreatedAt(),
            comment.GetContent(),
            comment.IsEdited(),
            comment.GetParent() != null ? comment.GetParent().GetID() : null
        );
    }

}
