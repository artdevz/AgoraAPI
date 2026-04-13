package com.agora.mappers;

import com.agora.dto.post.PostResponseDTO;
import com.agora.dto.user.UserSummaryDTO;
import com.agora.entities.PostEntity;
import com.agora.models.Post;

public class PostMapper {

    // DB -> API
    public static Post ToDomain(PostEntity entity) {
        if (entity == null) return null;

        Post post = new Post(
            entity.getId(),
            UserMapper.ToDomain(entity.getAuthor()),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getStatus()
        );

        return post;
    }

    // API -> DB
    public static PostEntity ToEntity(Post domain) {
        if (domain == null) return null;
        
        PostEntity entity = new PostEntity();
        entity.setId(domain.GetID());
        entity.setAuthor(UserMapper.ToEntity(domain.GetAuthor()));
        entity.setTitle(domain.GetTitle());
        entity.setDescription(domain.GetDescription());
        entity.setCreatedAt(domain.GetCreatedAt());
        entity.setStatus(domain.GetStatus());

        return entity;
    }

    // API -> OUT
    public static PostResponseDTO ToResponseDTO(Post domain) {
        if (domain == null) return null;

        return new PostResponseDTO(
            domain.GetID(),
            new UserSummaryDTO(
                domain.GetAuthor().GetID(),
                domain.GetAuthor().GetNickname()
            ),
            domain.GetTitle(),
            domain.GetDescription(),
            domain.GetCreatedAt(),
            domain.GetStatus()
        );
    }
    
}
