package com.agora.dto;

public record EmbeddingRequest(
    String model,
    String input
) {}
