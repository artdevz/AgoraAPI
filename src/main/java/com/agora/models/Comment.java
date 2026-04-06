package com.agora.models;

import java.time.LocalDate;
import java.util.UUID;

public class Comment {

    private static final int MAXIMUM_CONTENT_LENGTH = 2048;

    private UUID id;
    private Post post;
    private User user;
    private LocalDate createdAt;
    private String content;

    private Comment parent;

    public Comment() {}
    public Comment(
        UUID id,
        Post post,
        User user,
        LocalDate createdAt,
        String content,
        Comment parent
    ) {
        this.id = id;
        SetPost(post);
        SetUser(user);
        SetCreatedAt(createdAt);
        SetContent(content);
        SetParent(parent);
    }

    public UUID GetID() { return id; }
    public Post GetPost() { return post; }
    public User GetUser() { return user; }
    public LocalDate GetCreatedAt() { return createdAt; }
    public String GetContent() { return content; }
    public Comment GetParent() { return parent; }

    public void SetPost(Post post) {
        this.post = post;
    }

    public void SetUser(User user) {
        this.user = user;
    }

    public void SetCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void SetContent(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) throw new IllegalArgumentException("Content deve ter no máximo " + MAXIMUM_CONTENT_LENGTH + " caracteres");
        this.content = content;
    }

    public void SetParent(Comment parent) {
        if (parent != null && parent.GetParent() != null) {
            throw new IllegalArgumentException("Não é permitido criar um comentário com mais de um nível de resposta");
        }
        if (parent != null && !parent.GetPost().GetID().equals(this.post.GetID())) {
            throw new IllegalArgumentException("O comentário pai deve pertencer ao mesmo post do comentário filho");
        }
        if (parent != null && parent.GetID().equals(this.id)) {
            throw new IllegalArgumentException("Um comentário não pode ser pai de si mesmo");
        }
        this.parent = parent;
    }
    
}
