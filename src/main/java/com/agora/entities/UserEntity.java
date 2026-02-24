package com.agora.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.agora.enums.AuthProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDate createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column
    private String providerID;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts;

}
