package com.ricajust.jobsearchagent.controller;

import com.ricajust.jobsearchagent.service.OllamaClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final OllamaClientService ollamaClientService;

    public AgentController(OllamaClientService ollamaClientService) {
        this.ollamaClientService = ollamaClientService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        return ollamaClientService.chat(prompt);
    }
}
