// package com.agora.entities;

// import java.util.UUID;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "user_mutes")
// public class UserMuteEntity {
    
//     @Id
//     @GeneratedValue
//     private UUID id;

//     @ManyToOne
//     @Column(unique = true)
//     @JoinColumn(name = "muter_id")
//     private UserEntity muter;

//     @ManyToOne
//     @Column(unique = true)
//     @JoinColumn(name = "muted_id")
//     private UserEntity muted;

// }
