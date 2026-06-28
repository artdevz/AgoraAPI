package com.agora.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agora.entities.PostEntity;
@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    
    @Query(value = """
        SELECT *
        FROM posts p
        WHERE
            to_tsvector(
                'portuguese',
                coalesce(p.title, '') || ' ' || coalesce(p.content, '')
            )
            @@
            websearch_to_tsquery('portuguese', :query)
        ORDER BY
            ts_rank(
                to_tsvector(
                    'portuguese',
                    coalesce(p.title, '') || ' ' || coalesce(p.content, '')
                ),
                websearch_to_tsquery('portuguese', :query)
            ) DESC,
            p.created_at DESC
    """, nativeQuery = true)
    List<PostEntity> searchText(@Param("query") String query);

    @Query(value = """
        SELECT * FROM posts 
        WHERE embedding <=> CAST(:embedding AS vector) < 0.45
        ORDER BY embedding <=> CAST(:embedding AS vector) 
        LIMIT 20
    """, nativeQuery = true)
    List<PostEntity> searchSemantic(@Param("embedding") float[] embedding);

    @Query("SELECT p FROM Post p WHERE p.author.nickname = :nickname")
    List<PostEntity> findByAuthorNickname(String nickname);

    @Query("""
        SELECT p FROM Post p
        WHERE p.author.id NOT IN (
            SELECT m.muted.id
            FROM UserMuteEntity m
            WHERE m.muter.id = :currentUserID
        )
        ORDER BY p.createdAt DESC
    """)
    List<PostEntity> findFeedNew(UUID currentUserID);

}
