package com.agora.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.entities.UserMuteEntity;

public interface MuteRepository extends JpaRepository<UserMuteEntity, UUID> {

    boolean existsByMuterIdAndMutedId(UUID muterID, UUID mutedID);

    void deleteByMuterIdAndMutedId(UUID muterID, UUID mutedID);

    List<UserMuteEntity> findByMuterId(UUID muterID);

}
