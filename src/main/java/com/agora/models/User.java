package com.agora.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agora.enums.AuthProvider;
import com.agora.enums.UserStatus;

public class User {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MAXIMUM_NAME_LENGTH = 32;
    
    private UUID id;
    private String nickname;
    private String email;
    private String password;
    private OffsetDateTime createdAt;
    private UserStatus status;

    private AuthProvider provider;
    private String providerID; // Via Google

    public User() {}
    public User(Builder builder) {   
        this.id = builder.id;
        SetNickname(builder.nickname);
        SetEmail(builder.email);
        SetPassword(builder.password);
        SetCreatedAt(builder.createdAt);
        SetProvider(builder.provider);
        SetStatus(builder.status);
    }

    public UUID GetID() { return id; }
    public String GetNickname() { return nickname; }
    public String GetEmail() { return email; }
    public String GetPassword() { return password; }
    public OffsetDateTime GetCreatedAt() { return createdAt; }
    public UserStatus GetStatus() { return status; }

    public AuthProvider GetProvider() { return provider; }
    public String GetProviderID() { return providerID; }

    public void SetNickname(String nickname) {
        if (nickname.length() < MINIMUM_NAME_LENGTH || nickname.length() > MAXIMUM_NAME_LENGTH) throw new IllegalArgumentException("Nickname deve ter entre " + MINIMUM_NAME_LENGTH + " e " + MAXIMUM_NAME_LENGTH + " caracteres");
        this.nickname = nickname;
    }

    public void SetEmail(String email) {
        ValidateEmail(email);
        this.email = email;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public void SetCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void SetStatus(UserStatus status) {
        this.status = status;
    }

    public void SetProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public void SetProviderID(String googleID) {
        if (AuthProvider.GOOGLE != this.GetProvider()) throw new IllegalStateException("Apenas usuários GOOGLE podem ter providerID");
        this.providerID = googleID;
    }

    private void ValidateEmail(String email) { if (!(email.matches(EMAIL_REGEX))) throw new IllegalArgumentException("Formato de Email inválido"); }

    public static class Builder {
        private UUID id;
        private String nickname;
        private String email;
        private String password;
        private OffsetDateTime createdAt;
        private AuthProvider provider;
        private UserStatus status;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder provider(AuthProvider provider) {
            this.provider = provider;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
