package com.agora.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.comment.CommentResponseDTO;
import com.agora.dto.post.PostCreateDTO;
import com.agora.dto.post.PostResponseDTO;
import com.agora.dto.post.PostUpdateDTO;
import com.agora.mappers.CommentMapper;
import com.agora.mappers.PostMapper;
import com.agora.services.CommentService;
import com.agora.services.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid PostCreateDTO dto) {
        System.out.println("Creating post with title: " + dto.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.Create(dto).GetID().toString());
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> ReadAll() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.ReadAll().stream().map(PostMapper::ToResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> ReadByID(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(PostMapper.ToResponseDTO(postService.ReadByID(id)));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDTO>> ReadAllCommentsByID(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.ReadAllByPostID(id).stream().map(CommentMapper::ToResponseDTO).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Update(@PathVariable UUID id, @RequestBody @Valid PostUpdateDTO dto) {
        postService.Update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable UUID id) {
        postService.Delete(id);
        return ResponseEntity.ok().build();
    }
}
