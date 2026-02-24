package com.agora.mappers;

import com.agora.entities.UserEntity;
import com.agora.models.User;

public class UserMapper {
    
    public static User toDomain(UserEntity entity, boolean details) {
        if (entity == null) return null;
        User user = new User(
            entity.getId(),
            entity.getUsername(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getCreatedAt(),
            entity.getProvider()
        );

        if (details) {
            // Mostrar os Posts tbm
        }

        return user;
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.GetID());
        entity.setUsername(user.GetUsername());
        entity.setEmail(user.GetEmail());
        entity.setPassword(user.GetPassword());
        entity.setCreatedAt(user.GetCreatedAt());
        entity.setProvider(user.GetProvider());

        return entity;
    }

}
