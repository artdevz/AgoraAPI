// package com.agora.entities;

// import java.util.UUID;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "post_likes")
// public class PostLikeEntity {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private UUID id;

//     @ManyToOne
//     @Column(unique = true)
//     @JoinColumn(name = "user_id")
//     private UserEntity user;

//     @ManyToOne
//     @Column(unique = true)
//     @JoinColumn(name = "post_id")
//     private PostEntity post;

// }
