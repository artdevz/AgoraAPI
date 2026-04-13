package com.agora.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.agora.enums.SubmitStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "Post")
@Table(name = "posts")
public class PostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private String title;

    private String description;

    private OffsetDateTime createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubmitStatus status;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;

}
