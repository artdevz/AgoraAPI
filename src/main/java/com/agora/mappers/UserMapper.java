package com.agora.mappers;

import com.agora.dto.user.UserResponseDTO;
import com.agora.entities.UserEntity;
import com.agora.models.User;

public class UserMapper {
    
    // DB -> API
    public static User ToDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User(
            entity.getId(),
            entity.getNickname(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getCreatedAt(),
            entity.getProvider()
        );

        return user;
    }

    // API -> DB
    public static UserEntity ToEntity(User domain) {
        if (domain == null) return null;
        
        UserEntity entity = new UserEntity();
        entity.setId(domain.GetID());
        entity.setNickname(domain.GetNickname());
        entity.setEmail(domain.GetEmail());
        entity.setPassword(domain.GetPassword());
        entity.setCreatedAt(domain.GetCreatedAt());
        entity.setProvider(domain.GetProvider());

        return entity;
    }   

    // API -> OUT
    public static UserResponseDTO ToResponseDTO(User domain) {
        if (domain == null) return null;

        return new UserResponseDTO(
            domain.GetID(),
            domain.GetNickname(),
            domain.GetEmail(),
            domain.GetCreatedAt()
        );
    }

}
