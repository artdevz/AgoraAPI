package com.agora.entities;

import java.time.OffsetDateTime;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Comment")
@Table(name = "comments")
public class CommentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    private OffsetDateTime createdAt;

    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubmitStatus status;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

}
