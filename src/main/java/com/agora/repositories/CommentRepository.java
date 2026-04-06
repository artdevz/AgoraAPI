package com.agora.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.entities.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    
}
