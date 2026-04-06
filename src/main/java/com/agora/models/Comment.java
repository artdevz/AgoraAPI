package com.agora.models;

import java.time.LocalDate;
import java.util.UUID;

public class Comment {

    private static final int MAXIMUM_CONTENT_LENGTH = 2048;

    private UUID id;
    private Post post;
    private User user;
    private LocalDate time;
    private String content; // To-Do: Fazer pra Imagem/Vídeo/GIF...

    public Comment() {}
    public Comment(
        UUID id,
        Post post,
        User user,
        LocalDate time,
        String content
    ) {
        this.id = id;
        SetPost(post);
        SetUser(user);
        SetTime(time);
        SetContent(content);
    }

    public UUID GetID() { return id; }
    public Post GetPost() { return post; }
    public User GetUser() { return user; }
    public LocalDate GetTime() { return time; }
    public String GetContent() { return content; }

    public void SetPost(Post post) {
        this.post = post;
    }

    public void SetUser(User user) {
        this.user = user;
    }

    public void SetTime(LocalDate time) {
        this.time = time;
    }

    public void SetContent(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) throw new IllegalArgumentException("Content deve ter no máximo " + MAXIMUM_CONTENT_LENGTH + " caracteres");
        this.content = content;
    }
    
}
