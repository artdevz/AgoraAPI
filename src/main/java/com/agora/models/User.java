package com.agora.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MAXIMUM_NAME_LENGTH = 32;
    
    private UUID id;
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;

    private List<Post> posts = new ArrayList<>();

    public User() {}
    public User(
        UUID id,
        String username,
        String email,
        String password,
        LocalDate createdAt
    ) {
        this.id = id;
        SetUsername(username);
        SetEmail(email);
        SetPassword(password);
        SetCreatedAt(createdAt);
    }

    public UUID GetID() { return id; }
    public String GetUsername() { return username; }
    public String GetEmail() { return email; }
    public String GetPassword() { return password; }
    public LocalDate GetCreatedAt() { return createdAt; }

    public List<Post> GetPosts() { return posts; }

    public void SetUsername(String username) {
        if (username.length() < MINIMUM_NAME_LENGTH || username.length() > MAXIMUM_NAME_LENGTH) throw new IllegalArgumentException("Username deve ter entre " + MINIMUM_NAME_LENGTH + " e " + MAXIMUM_NAME_LENGTH + " caracteres");
        this.username = username;
    }

    public void SetEmail(String email) {
        ValidateEmail(email);
        this.email = email;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public void SetCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    private void ValidateEmail(String email) { if (!(email.matches(EMAIL_REGEX))) throw new IllegalArgumentException("Formato de Email inválido"); }

}
