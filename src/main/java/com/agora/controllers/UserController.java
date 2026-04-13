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
import com.agora.dto.post.PostResponseDTO;
import com.agora.dto.user.UserCreateDTO;
import com.agora.dto.user.UserResponseDTO;
import com.agora.dto.user.UserUpdateDTO;
import com.agora.mappers.CommentMapper;
import com.agora.mappers.PostMapper;
import com.agora.mappers.UserMapper;
import com.agora.services.CommentService;
import com.agora.services.PostService;
import com.agora.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.Create(dto).GetID().toString());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> ReadAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.ReadAll().stream().map(UserMapper::ToResponseDTO).toList());
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<UserResponseDTO> ReadByNickname(@PathVariable String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.ToResponseDTO(userService.ReadByNickname(nickname)));
    }

    @GetMapping("/{nickname}/posts")
    public ResponseEntity<List<PostResponseDTO>> ReadAllPostsByNickname(@PathVariable String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.ReadAllByAuthorNickname(nickname).stream().map(PostMapper::ToResponseDTO).toList());
    }

    @GetMapping("/{nickname}/comments")
    public ResponseEntity<List<CommentResponseDTO>> ReadAllCommentsByNickname(@PathVariable String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.ReadAllByAuthorNickname(nickname).stream().map(CommentMapper::ToResponseDTO).toList());
    }

    @PutMapping()
    public ResponseEntity<Void> Update(UserUpdateDTO dto) {
        userService.Update(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Update(@PathVariable UUID id) {
        userService.UpdateStatus(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> Delete() {
        userService.Delete();
        return ResponseEntity.ok().build();
    }

}
