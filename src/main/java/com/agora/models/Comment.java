package com.agora.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.agora.enums.SubmitStatus;

public class Comment {

    private static final int MAXIMUM_CONTENT_LENGTH = 2048;

    private UUID id;
    private Post post;
    private User author;
    private OffsetDateTime createdAt;
    private String content;
    private SubmitStatus status;

    private Comment parent;
    private List<Comment> replies = new ArrayList<>();

    public Comment() {}
    public Comment(
        UUID id,
        Post post,
        User author,
        OffsetDateTime createdAt,
        String content,
        SubmitStatus status,
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
        SetStatus(status);
        SetParent(parent);
    }

    public UUID GetID() { return id; }
    public Post GetPost() { return post; }
    public User GetAuthor() { return author; }
    public OffsetDateTime GetCreatedAt() { return createdAt; }
    public String GetContent() { return content; }
    public SubmitStatus GetStatus() { return status; }
    public Comment GetParent() { return parent; }
    public List<Comment> GetReplies() { return replies; }

    // Utilizado pra fazer o Composite do Parent
    public void SetID(UUID id) {
        this.id = id;
    }

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

    public void SetStatus(SubmitStatus status) {
        this.status = status;
    }

    public void SetParent(Comment parent) {
        if (parent != null && parent.isAncestorOf(this)) {
            throw new IllegalArgumentException("Ciclo detectado na árvore de comentários");
        }

        if (this.parent != null) {
            this.parent.replies.remove(this);
        }

        this.parent = parent;

        if (parent != null && !parent.replies.contains(this)) {
            parent.replies.add(this);
        }
    }

    public void AddReply(Comment reply) {
        if (reply == null) return;

        reply.SetParent(this);
    }

    private boolean isAncestorOf(Comment node) {
        Comment current = this;

        while (current != null) {
            if (current == node) return true;
            current = current.parent;
        }
        return false;
    }
    
}
