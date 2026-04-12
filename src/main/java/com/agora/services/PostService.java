package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.agora.dto.post.PostCreateDTO;
import com.agora.mappers.PostMapper;
import com.agora.models.Post;
import com.agora.models.User;
import com.agora.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final UserService userService;

    public Post Create(PostCreateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());
        Post post = new Post(
            null, // ID
            user,
            dto.title(),
            dto.description(),
            OffsetDateTime.now()
        );
        return PostMapper.ToDomain(postRepository.save(PostMapper.ToEntity(post)));
    }

    public List<Post> ReadAll() {
        return postRepository.findAll().stream().map(PostMapper::ToDomain).toList();
    }

    public Post ReadByID(UUID id) {
        return PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post não encontrado")));
    }

    public List<Post> ReadAllByAuthorNickname(String nickname) {
        return (postRepository.findByAuthorNickname(nickname).stream().map(PostMapper::ToDomain).toList());
    }

}
