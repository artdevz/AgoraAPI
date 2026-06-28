package com.agora.dto;

import java.util.List;

public record EmbeddingResponse(
    List<List<Float>> embeddings
) {}
