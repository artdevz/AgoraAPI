package com.agora.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.enums.SubmitStatus;

public class Post {

    private static final int MINIMUM_TITLE_LENGTH = 1;
    private static final int MAXIMUM_TITLE_LENGTH = 64;
    private static final int MAXIMUM_DESCRIPTION_LENGTH = 256;
    
    private UUID id;
    private User author;
    private String title;
    private String description;
    private OffsetDateTime createdAt;
    private SubmitStatus status;

    public Post() {}
    public Post(
        UUID id,
        User author,
        String title,
        String description,
        OffsetDateTime createdAt,
        SubmitStatus status
    ) {
        this.id = id;
        this.author = author;
        SetTitle(title);
        SetDescription(description);
        SetCreatedAt(createdAt);
        SetStatus(status);
    }

    public UUID GetID() { return id; }
    public User GetAuthor() { return author; }
    public String GetTitle() { return title; }
    public String GetDescription() { return description; }
    public OffsetDateTime GetCreatedAt() { return createdAt; }
    public SubmitStatus GetStatus() { return status; }

    public void SetAuthorID(User author) {
        this.author = author;
    }

    public void SetTitle(String title) {
        if (title.length() < MINIMUM_TITLE_LENGTH || title.length() > MAXIMUM_TITLE_LENGTH) throw new IllegalArgumentException("Title deve ter entre " + MINIMUM_TITLE_LENGTH + " e " + MAXIMUM_TITLE_LENGTH + " caracteres");
        this.title = title;
    }

    public void SetDescription(String description) {
        if (description.length() > MAXIMUM_DESCRIPTION_LENGTH) throw new IllegalArgumentException("Description deve ter no máximo " + MAXIMUM_DESCRIPTION_LENGTH + " caracteres");
        this.description = description;
    }

    public void SetCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void SetStatus(SubmitStatus status) {
        this.status = status;
    }

}
