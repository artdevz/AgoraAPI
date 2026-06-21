package com.agora.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agora.repositories.MuteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuteService {
    
    private final MuteRepository muteRepository;
    
    public void Mute(UUID userID) {}

    public void Unmute(UUID userID) {}

}
