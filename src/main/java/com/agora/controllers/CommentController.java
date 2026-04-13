package com.agora.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.dto.comment.CommentUpdateDTO;
import com.agora.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid CommentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.Create(dto).GetID().toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Update(@PathVariable UUID id, @RequestBody @Valid CommentUpdateDTO dto) {
        commentService.Update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable UUID id) {
        commentService.Delete(id);
        return ResponseEntity.ok().build();
    }

}
