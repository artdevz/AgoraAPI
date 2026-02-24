package com.agora.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agora.entities.UserEntity;
import com.agora.enums.AuthProvider;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    
    Optional<UserEntity> findByProviderAndProviderID(
        AuthProvider provider,
        String providerID
    );

    Optional<UserEntity> findByEmailAndProvider(
        String email,
        AuthProvider provider
    );

    Optional<UserEntity> findByEmail(String email);

    @Query("""
            SELECT u FROM User u
            LEFT JOIN FETCH u.posts
            WHERE u.id = :id            
        """)
    Optional<UserEntity> findByIdWithPosts(UUID id);

}
