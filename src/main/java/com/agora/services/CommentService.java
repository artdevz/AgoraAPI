package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agora.dto.comment.CommentCreateDTO;
import com.agora.dto.comment.CommentUpdateDTO;
import com.agora.enums.SubmitStatus;
import com.agora.mappers.CommentMapper;
import com.agora.models.Comment;
import com.agora.models.User;
import com.agora.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Comment Create(CommentCreateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        Comment parent = null;
        if (dto.parentID() != null) {
            parent = CommentMapper.ToDomain(commentRepository.findById(dto.parentID()).orElseThrow(() -> new RuntimeException("Parent comment not found")));
        }

        Comment comment = new Comment(
            null, // ID
            postService.ReadByID(dto.postID()),
            user,
            OffsetDateTime.now(), 
            dto.content(),
            SubmitStatus.ACTIVE,
            parent
        );

        if (comment.GetPost().GetStatus() == SubmitStatus.DELETED) throw new ResponseStatusException(HttpStatus.CONFLICT, "Can't comment on a deleted post");

        return CommentMapper.ToDomain(commentRepository.save(CommentMapper.ToEntity(comment)));
    }

    public List<Comment> ReadAllByPostID(UUID id) {
        return commentRepository.findByPostID(id).stream().map(CommentMapper::ToDomain).toList();
    }

    public List<Comment> ReadAllByAuthorNickname(String nickname) {
        return commentRepository.findAllByAuthorNickname(nickname).stream().map(CommentMapper::ToDomain).toList();
    }

    public void Update(UUID id, CommentUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());
        
        Comment comment = CommentMapper.ToDomain(commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found")));
        if (comment.GetStatus() == SubmitStatus.DELETED) return;

        if (!comment.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");

        comment.SetContent(dto.content());
        comment.SetStatus(SubmitStatus.EDITED);

        commentRepository.save(CommentMapper.ToEntity(comment));
    }

    public void Delete(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());

        Comment comment = CommentMapper.ToDomain(commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found")));

        if (!comment.GetAuthor().GetID().equals(user.GetID())) throw new RuntimeException("Unauthorized");

        comment.SetStatus(SubmitStatus.DELETED);

        commentRepository.save(CommentMapper.ToEntity(comment));
    }

}
