package com.agora.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agora.dto.post.PostCreateDTO;
import com.agora.dto.post.PostResponseDTO;
import com.agora.mappers.PostMapper;
import com.agora.models.Post;
import com.agora.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postR;

    public Post Create(PostCreateDTO dto) {
        Post post = new Post(
            null, // ID
            dto.title(),
            dto.description(),
            LocalDate.now()
        );
        return PostMapper.toDomain(postR.save(PostMapper.toEntity(post)), false);
    }

    public List<PostResponseDTO> ReadAll() {
        return postR.findAll().stream().map(PostMapper::ToResponseDTO).toList();
    }

}
