package com.agora.models;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Comment {

    private static final int MAXIMUM_CONTENT_LENGTH = 2048;

    private UUID id;
    private Post post;
    private User author;
    private OffsetDateTime createdAt;
    private String content;
    private boolean edited;
    private boolean deleted;

    private Comment parent;

    public Comment() {}
    public Comment(
        UUID id,
        Post post,
        User author,
        OffsetDateTime createdAt,
        String content,
        boolean edited,
        boolean deleted,
        Comment parent
    ) {
        if (post == null) throw new IllegalArgumentException("Post não pode ser nulo");
        if (author == null) throw new IllegalArgumentException("Author não pode ser nulo");
        if (content == null) throw new IllegalArgumentException("Content não pode ser nulo");

        this.id = id;
        SetPost(post);
        SetAuthor(author);
        SetCreatedAt(createdAt);
        SetContent(content);
        SetEdited(edited);
        SetDeleted(deleted);
        SetParent(parent);
    }

    public UUID GetID() { return id; }
    public Post GetPost() { return post; }
    public User GetAuthor() { return author; }
    public OffsetDateTime GetCreatedAt() { return createdAt; }
    public String GetContent() { return content; }
    public boolean IsEdited() { return edited; }
    public boolean isDeleted() { return deleted; }
    public Comment GetParent() { return parent; }

    public void SetPost(Post post) {
        this.post = post;
    }

    public void SetAuthor(User author) {
        this.author = author;
    }

    public void SetCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void SetContent(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) throw new IllegalArgumentException("Content deve ter no máximo " + MAXIMUM_CONTENT_LENGTH + " caracteres");
        this.content = content;
    }

    public void SetEdited(boolean edited) {
        this.edited = edited;
    }

    public void SetDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void SetParent(Comment parent) {
        // if (parent != null && parent.GetParent() != null) {
        //     throw new IllegalArgumentException("Não é permitido criar um comentário com mais de um nível de resposta");
        // }
        // if (parent != null && !parent.GetPost().GetID().equals(this.post.GetID())) {
        //     throw new IllegalArgumentException("O comentário pai deve pertencer ao mesmo post do comentário filho");
        // }
        // if (parent != null && parent.GetID().equals(this.id)) {
        //     throw new IllegalArgumentException("Um comentário não pode ser pai de si mesmo");
        // }
        this.parent = parent;
    }
    
}
