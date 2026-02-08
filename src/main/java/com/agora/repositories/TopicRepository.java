package com.agora.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.entities.TopicEntity;

public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {
    
}
