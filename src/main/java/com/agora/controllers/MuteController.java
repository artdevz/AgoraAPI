package com.agora.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.services.MuteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mute")
public class MuteController {
    
    private final MuteService muteService;

    @PostMapping("/{userID}")
    public ResponseEntity<Void> Mute(@PathVariable UUID userID) {
        muteService.Mute(userID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> Unmute(@PathVariable UUID userID) {
        muteService.Mute(userID);
        return ResponseEntity.ok().build();
    }

}
