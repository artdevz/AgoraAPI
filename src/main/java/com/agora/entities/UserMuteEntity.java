package com.agora.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user_mutes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"muter_id, muted_id"})
    }
)
public class UserMuteEntity {
    
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "muter_id", nullable = false)
    private UserEntity muter;

    @ManyToOne
    @JoinColumn(name = "muted_id", nullable = false)
    private UserEntity muted;

}
