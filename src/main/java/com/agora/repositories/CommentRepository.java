package com.agora.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agora.entities.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postID AND c.author.id NOT IN (SELECT m.muted.id FROM UserMuteEntity m WHERE m.muter.id = :currentUserID) ORDER BY c.createdAt ASC")
    List<CommentEntity> findByPostID(UUID postID, UUID currentUserID);

    @Query("SELECT c FROM Comment c WHERE c.author.nickname = :nickname")
    List<CommentEntity> findAllByAuthorNickname(String nickname);

}
