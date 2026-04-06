package com.agora.mappers;

import com.agora.entities.CommentEntity;
import com.agora.models.Comment;

public class CommentMapper {
    
    public static Comment toDomain(CommentEntity entity) {
        if (entity == null) return null;

        Comment comment = new Comment(
            entity.getId(),
            null, // Post
            null, // User
            entity.getCreatedAt(),
            entity.getContent(),
            null  // Parent
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
        // Parent

        return entity;
    }

}
