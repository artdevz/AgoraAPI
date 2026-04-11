package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.dto.comment.CommentUpdateDTO;
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
            OffsetDateTime.now(), 
            dto.content(),
            false, // Edited
            false,
            parent
        );
        return CommentMapper.toDomain(commentR.save(CommentMapper.toEntity(comment)));
    }

    public List<Comment> ReadAllByPostID(UUID id) {
        return commentR.findByPostID(id).stream().map(CommentMapper::toDomain).toList();
    }

    public void Update(UUID id, CommentUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userS.ReadByEmail(auth.getName());
        
        Comment comment = CommentMapper.toDomain(commentR.findById(id).orElseThrow(() -> new RuntimeException("Comment not found")));
        if (comment.isDeleted()) return;

        if (!comment.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");

        comment.SetContent(dto.content());
        comment.SetEdited(true);

        commentR.save(CommentMapper.toEntity(comment));
    }

    public void Delete(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userS.ReadByEmail(auth.getName());

        Comment comment = CommentMapper.toDomain(commentR.findById(id).orElseThrow(() -> new RuntimeException("Comment not found")));

        if (!comment.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");

        comment.SetContent("[Deleted]");
        comment.SetDeleted(true);

        commentR.save(CommentMapper.toEntity(comment));
    }

}
