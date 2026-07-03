package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agora.dto.post.PostCreateDTO;
import com.agora.dto.post.PostUpdateDTO;
import com.agora.entities.PostEntity;
import com.agora.enums.SubmitStatus;
import com.agora.enums.UserStatus;
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
    private final EmbeddingService embeddingService;

    public Post Create(PostCreateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());
        
        Post post = Post.builder()
            .id(null)
            .author(user)
            .title(dto.title())
            .content(dto.content())
            .createdAt(OffsetDateTime.now())
            .status(SubmitStatus.ACTIVE)
        .build();

        PostEntity postEntity = PostMapper.ToEntity(post);
        
        float[] embedding = embeddingService.Generate(String.join("\n", post.GetTitle(), post.GetContent()));

        postEntity.setEmbedding(embedding);

        return PostMapper.ToDomain(postRepository.save(postEntity));
    }

    public List<Post> ReadAll() {
        return postRepository.findAll().stream().map(PostMapper::ToDomain).toList();
    }

    public Post ReadByID(UUID id) {
        return PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found")));
    }

    public List<Post> Search(String query) {
        float[] embedding = embeddingService.Generate(query);

        return postRepository.searchSemantic(embedding).stream().map(PostMapper::ToDomain).toList(); // COM IA
        // return postRepository.searchText(query).stream().map(PostMapper::ToDomain).toList(); // SEM IA
    }

    public List<Post> ReadNewPosts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        if (user.GetStatus() != UserStatus.ACTIVE) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sua conta está suspensa");
        
        return postRepository.findFeedNew(user.GetID()).stream().map(PostMapper::ToDomain).toList();
    }

    public List<Post> ReadAllByAuthorNickname(String nickname) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        if (user.GetStatus() != UserStatus.ACTIVE) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sua conta está suspensa");

        return (postRepository.findByAuthorNickname(nickname).stream().map(PostMapper::ToDomain).toList());
    }

    public void Update(UUID id, PostUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        if (user.GetStatus() != UserStatus.ACTIVE) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sua conta está suspensa");

        Post post = PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")));
        if (post.GetStatus() == SubmitStatus.DELETED) return;

        if (!post.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");
        
        post.SetContent(dto.content());
        post.SetStatus(SubmitStatus.EDITED);

        postRepository.save(PostMapper.ToEntity(post));
    }

    public void Delete(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        if (user.GetStatus() != UserStatus.ACTIVE) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sua conta está suspensa");

        Post post = PostMapper.ToDomain(postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")));

        if (!post.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");
        
        post.SetStatus(SubmitStatus.DELETED);

        postRepository.save(PostMapper.ToEntity(post));
    }

}
