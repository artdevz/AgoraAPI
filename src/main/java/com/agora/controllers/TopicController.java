package com.agora.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.topic.TopicCreateDTO;
import com.agora.dto.topic.TopicResponseDTO;
import com.agora.services.TopicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topic")
public class TopicController {
    
    private final TopicService topicS;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid TopicCreateDTO dto) {
        System.out.println("Creating topic with title: " + dto.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(topicS.Create(dto).GetID().toString());
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> ReadAll() {
        return ResponseEntity.status(HttpStatus.OK).body(topicS.ReadAll());
    }

}
