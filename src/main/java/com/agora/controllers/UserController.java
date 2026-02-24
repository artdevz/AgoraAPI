package com.agora.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.user.UserCreateDTO;
import com.agora.dto.user.UserResponseDTO;
import com.agora.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userS;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userS.Create(dto).GetID().toString());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> ReadAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userS.ReadAll());
    }

}
