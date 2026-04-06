package com.agora.mappers;

import com.agora.dto.topic.TopicResponseDTO;
import com.agora.entities.TopicEntity;
import com.agora.models.Topic;

public class TopicMapper {

    public static Topic toDomain(TopicEntity entity, boolean details) {
        if (entity == null) return null;

        Topic topic = new Topic(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt()
        );

        if (details) {
            // Mostrar os Posts tbm
        }

        return topic;
    }

    public static TopicEntity toEntity(Topic topic) {
        if (topic == null) return null;
        
        TopicEntity entity = new TopicEntity();
        entity.setId(topic.GetID());
        entity.setTitle(topic.GetTitle());
        entity.setDescription(topic.GetDescription());
        entity.setCreatedAt(topic.GetCreatedAt());

        return entity;
    }

    public static TopicResponseDTO ToResponseDTO(TopicEntity entity) {
        if (entity == null) return null;

        return new TopicResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt()
        );
    }
    
}
