package com.agora.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.mappers.CommentMapper;
import com.agora.models.Comment;
import com.agora.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentR;
    private final PostService postS;
    private final UserService userS;

    public Comment Create(CommentCreateDTO dto) {
        Comment comment = new Comment(
            null, // ID
            postS.ReadByID(dto.postID()),
            userS.ReadByID(dto.userID()),
            LocalDate.now(), 
            dto.content(),
            null  // Parent
        );
        return CommentMapper.toDomain(commentR.save(CommentMapper.toEntity(comment)));
    }

}
