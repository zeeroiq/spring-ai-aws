package com.shri.spring.springaiaws.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class AdoptionController {

    private final ChatClient chatClient;
    private final Map<String, PromptChatMemoryAdvisor> advisorMap = new ConcurrentHashMap<>();

    @GetMapping("/{user}/inquire")
    public String inquire(@PathVariable(name = "user") String user,
                           @RequestParam String question) {
        var advisor = this.advisorMap.computeIfAbsent(user,
                _ -> PromptChatMemoryAdvisor.builder(new InMemoryChatMemory()).build());

        return this.chatClient
                .prompt()
                .user(question)
                .advisors(advisor)
                .call()
                .content();
    }

}
