package com.agora.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.enums.SubmitStatus;

public class Post {

    private static final int MINIMUM_TITLE_LENGTH = 1;
    private static final int MAXIMUM_TITLE_LENGTH = 64;
    private static final int MAXIMUM_CONTENT_LENGTH = 256;
    
    private UUID id;
    private User author;
    private String title;
    private String content;
    private OffsetDateTime createdAt;
    private SubmitStatus status;

    public Post() {}
    public Post(Builder builder) {
        this.id = builder.id;
        this.author = builder.author;
        SetTitle(builder.title);
        SetContent(builder.content);
        SetCreatedAt(builder.createdAt);
        SetStatus(builder.status);
    }

    public UUID GetID() { return id; }
    public User GetAuthor() { return author; }
    public String GetTitle() { return title; }
    public String GetContent() { return content; }
    public OffsetDateTime GetCreatedAt() { return createdAt; }
    public SubmitStatus GetStatus() { return status; }

    public void SetAuthorID(User author) {
        this.author = author;
    }

    public void SetTitle(String title) {
        if (title.length() < MINIMUM_TITLE_LENGTH || title.length() > MAXIMUM_TITLE_LENGTH) throw new IllegalArgumentException("Title deve ter entre " + MINIMUM_TITLE_LENGTH + " e " + MAXIMUM_TITLE_LENGTH + " caracteres");
        this.title = title;
    }

    public void SetContent(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) throw new IllegalArgumentException("Content deve ter no máximo " + MAXIMUM_CONTENT_LENGTH + " caracteres");
        this.content = content;
    }

    public void SetCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void SetStatus(SubmitStatus status) {
        this.status = status;
    }

    public static class Builder {
        private UUID id;
        private User author;
        private String title;
        private String content;
        private OffsetDateTime createdAt;
        private SubmitStatus status;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder status(SubmitStatus status) {
            this.status = status;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
