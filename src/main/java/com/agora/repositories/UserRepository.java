package com.agora.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    
}
