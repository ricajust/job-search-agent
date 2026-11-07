package com.ricajust.jobsearchagent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Service
public class OllamaClientService {

    private final RestClient restClient;
    private final String model;
    private final AgentInstructionsService agentInstructionsService;

    public OllamaClientService(
            @Value("${ollama.base-url}") String baseUrl,
            @Value("${ollama.model}") String model,
            AgentInstructionsService agentInstructionsService
    ) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.model = model;
        this.agentInstructionsService = agentInstructionsService;
    }

    public String chat(String prompt) {

        Map<String, Object> request = Map.of(
                "model", model,
                "stream", false,
                "think", false,
                "messages", List.of(
                        Map.of("role", "system", "content", agentInstructionsService.getInstructions()),
                        Map.of("role", "user", "content", prompt)
                )
        );

        JsonNode response = restClient.post()
                .uri("api/chat")
                .body(request)
                .retrieve()
                .body(JsonNode.class);

        return response.path("message")
                .path("content")
                .asText();
    }
}
