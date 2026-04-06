package com.agora.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentS;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid CommentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentS.Create(dto).GetID().toString());
    }

}
