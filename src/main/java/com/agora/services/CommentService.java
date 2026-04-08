package com.agora.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.mappers.CommentMapper;
import com.agora.models.Comment;
import com.agora.models.User;
import com.agora.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentR;
    private final PostService postS;
    private final UserService userS;

    public Comment Create(CommentCreateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userS.ReadByEmail(auth.getName());

        Comment parent = null;
        if (dto.parentID() != null) {
            parent = CommentMapper.toDomain(commentR.findById(dto.parentID()).orElseThrow(() -> new RuntimeException("Parent comment not found")));
        }

        Comment comment = new Comment(
            null, // ID
            postS.ReadByID(dto.postID()),
            user,
            LocalDate.now(), 
            dto.content(),
            false, // Edited
            parent
        );
        return CommentMapper.toDomain(commentR.save(CommentMapper.toEntity(comment)));
    }

    public List<Comment> ReadAllByPostID(UUID id) {
        return commentR.findByPostID(id).stream().map(CommentMapper::toDomain).toList();
    }

}
