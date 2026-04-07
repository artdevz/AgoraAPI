package com.agora.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agora.entities.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postID")
    List<CommentEntity> findByPostID(UUID postID);

}
