package com.agora.models;

import java.time.LocalDate;
import java.util.UUID;

public class Post {

    private static final int MAXIMUM_CONTENT_LENGTH = 2048;

    private UUID id;
    private Topic topic;
    private User user;
    private LocalDate time;
    private String content; // To-Do: Fazer pra Imagem/Vídeo/GIF...

    public Post() {}
    public Post(
        UUID id,
        Topic topic,
        User user,
        LocalDate time,
        String content
    ) {
        this.id = id;
        SetTopic(topic);
        SetUser(user);
        SetTime(time);
        SetContent(content);
    }

    public UUID GetID() { return id; }
    public Topic GetTopic() { return topic; }
    public User GetUser() { return user; }
    public LocalDate GetTime() { return time; }
    public String GetContent() { return content; }

    public void SetTopic(Topic topic) {
        this.topic = topic;
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
