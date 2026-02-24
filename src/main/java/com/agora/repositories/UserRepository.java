package com.agora.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

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

}
