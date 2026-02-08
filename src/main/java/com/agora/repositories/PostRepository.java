package com.agora.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    
}
