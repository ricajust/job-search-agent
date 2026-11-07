package com.ricajust.jobsearchagent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class AgentInstructionsService {

    private final String instructions;

    public AgentInstructionsService(@Value("classpath:agent/AGENTS.md") Resource resource) throws IOException {
        try(var inputStream = resource.getInputStream()) {
            this.instructions = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public String getInstructions() {
        return instructions;
    }
}
