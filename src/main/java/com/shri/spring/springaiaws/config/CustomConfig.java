package com.shri.spring.springaiaws.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    protected ChatClient chatClient(ChatClient.Builder chatClient) {
        return chatClient.build();
    }

}
