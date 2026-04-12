package com.agora.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agora.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    
    @Query("SELECT p FROM Post p WHERE p.author.nickname = :nickname")
    List<PostEntity> findByAuthorNickname(String nickname);

}
