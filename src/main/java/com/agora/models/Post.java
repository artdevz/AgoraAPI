package com.agora.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post {

    private static final int MINIMUM_TITLE_LENGTH = 1;
    private static final int MAXIMUM_TITLE_LENGTH = 64;
    private static final int MAXIMUM_DESCRIPTION_LENGTH = 256;
    
    private UUID id;
    private String title;
    private String description;
    private LocalDate createdAt;

    private List<Comment> comments = new ArrayList<>();

    public Post() {}
    public Post(
        UUID id,
        String title,
        String description,
        LocalDate createdAt
    ) {
        this.id = id;
        SetTitle(title);
        SetDescription(description);
        SetCreatedAt(createdAt);
    }

    public UUID GetID() { return id; }
    public String GetTitle() { return title; }
    public String GetDescription() { return description; }
    public LocalDate GetCreatedAt() { return createdAt; }

    public List<Comment> GetComments() { return comments; }

    public void SetTitle(String title) {
        if (title.length() < MINIMUM_TITLE_LENGTH || title.length() > MAXIMUM_TITLE_LENGTH) throw new IllegalArgumentException("Title deve ter entre " + MINIMUM_TITLE_LENGTH + " e " + MAXIMUM_TITLE_LENGTH + " caracteres");
        this.title = title;
    }

    public void SetDescription(String description) {
        if (description.length() > MAXIMUM_DESCRIPTION_LENGTH) throw new IllegalArgumentException("Description deve ter no máximo " + MAXIMUM_DESCRIPTION_LENGTH + " caracteres");
        this.description = description;
    }

    public void SetCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
