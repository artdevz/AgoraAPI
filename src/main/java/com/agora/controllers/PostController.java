package com.agora.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.post.PostCreateDTO;
import com.agora.dto.post.PostResponseDTO;
import com.agora.services.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postS;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid PostCreateDTO dto) {
        System.out.println("Creating post with title: " + dto.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(postS.Create(dto).GetID().toString());
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> ReadAll() {
        return ResponseEntity.status(HttpStatus.OK).body(postS.ReadAll());
    }

}
