package com.agora.mappers;

import com.agora.dto.post.PostResponseDTO;
import com.agora.entities.PostEntity;
import com.agora.models.Post;

public class PostMapper {

    public static Post toDomain(PostEntity entity, boolean details) {
        if (entity == null) return null;

        Post post = new Post(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt()
        );

        if (details) {
            // Mostrar os Posts tbm
        }

        return post;
    }

    public static PostEntity toEntity(Post post) {
        if (post == null) return null;
        
        PostEntity entity = new PostEntity();
        entity.setId(post.GetID());
        entity.setTitle(post.GetTitle());
        entity.setDescription(post.GetDescription());
        entity.setCreatedAt(post.GetCreatedAt());

        return entity;
    }

    public static PostResponseDTO ToResponseDTO(PostEntity entity) {
        if (entity == null) return null;

        return new PostResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt()
        );
    }
    
}
