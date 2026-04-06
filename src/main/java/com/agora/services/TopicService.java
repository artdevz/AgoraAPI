package com.agora.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agora.dto.topic.TopicCreateDTO;
import com.agora.dto.topic.TopicResponseDTO;
import com.agora.mappers.TopicMapper;
import com.agora.models.Topic;
import com.agora.repositories.TopicRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TopicService {
    
    private final TopicRepository topicR;

    public Topic Create(TopicCreateDTO dto) {
        Topic topic = new Topic(
            null, // ID
            dto.title(),
            dto.description(),
            LocalDate.now()
        );
        return TopicMapper.toDomain(topicR.save(TopicMapper.toEntity(topic)), false);
    }

    public List<TopicResponseDTO> ReadAll() {
        return topicR.findAll().stream().map(TopicMapper::ToResponseDTO).toList();
    }

}
