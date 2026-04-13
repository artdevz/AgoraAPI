package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.agora.dto.post.PostCreateDTO;
import com.agora.dto.post.PostUpdateDTO;
import com.agora.enums.SubmitStatus;
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
            OffsetDateTime.now(),
            SubmitStatus.ACTIVE
        );
        return PostMapper.ToDomain(postRepository.save(PostMapper.ToEntity(post)));
    }

    public List<Post> ReadAll() {
        return postRepository.findAll().stream().map(PostMapper::ToDomain).toList();
    }

    public Post ReadByID(UUID id) {
        return PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found")));
    }

    public List<Post> ReadAllByAuthorNickname(String nickname) {
        return (postRepository.findByAuthorNickname(nickname).stream().map(PostMapper::ToDomain).toList());
    }

    public void Update(UUID id, PostUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        Post post = PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")));
        if (post.GetStatus() == SubmitStatus.DELETED) return;

        if (!post.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");
        
        post.SetDescription(dto.description());
        post.SetStatus(SubmitStatus.EDITED);

        postRepository.save(PostMapper.ToEntity(post));
    }

    public void Delete(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        Post post = PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")));

        if (!post.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");
        
        post.SetStatus(SubmitStatus.DELETED);

        postRepository.save(PostMapper.ToEntity(post));
    }

}
