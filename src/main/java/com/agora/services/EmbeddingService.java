package com.agora.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.agora.dto.EmbeddingRequest;
import com.agora.dto.EmbeddingResponse;

@Service
public class EmbeddingService {
    
    private static final String MODEL = "bge-m3";

    private final RestClient client;

    public EmbeddingService() {
        this.client = RestClient.builder().baseUrl("http://localhost:11434").build();
    }

    public float[] Generate(String text) {
        EmbeddingResponse response = client.post()
            .uri("/api/embed")
            .body(new EmbeddingRequest(MODEL, text))
            .retrieve()
            .body(EmbeddingResponse.class);

        if (response == null || response.embeddings().isEmpty()) {
            throw new RuntimeException("Embedding não retornado pelo Ollama");
        }

        var vector = response.embeddings().get(0);

        float[] embedding = new float[vector.size()];

        for (int i = 0; i < vector.size(); i++) {
            embedding[i] = vector.get(i);
        }

        return embedding;
    }

}
