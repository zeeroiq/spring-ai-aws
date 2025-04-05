package com.shri.spring.springaiaws.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class CustomConfig {

    @Value("classpath:/templates/prompt-template.st")
    private Resource defaultSystemPrompt;

    @Bean
    protected ChatClient chatClient(ChatClient.Builder chatClient) {
        return chatClient
                .defaultSystem(defaultSystemPrompt)
                .build();
    }

}
