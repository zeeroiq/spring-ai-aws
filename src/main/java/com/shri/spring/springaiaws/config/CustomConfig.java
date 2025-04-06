package com.shri.spring.springaiaws.config;

import com.shri.spring.springaiaws.repository.PetRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class CustomConfig {

    @Value("classpath:/templates/prompt-template.st")
    private Resource defaultSystemPrompt;

    @Bean
    protected ChatClient chatClient(ChatClient.Builder chatClient,
                                    PetRepository petRepository,
                                    VectorStore vectorStore) {

        petRepository.findAll().forEach(pet -> {
            Document petDocument = new Document("id: %s, name: %s, description: %s".formatted(pet.id(), pet.name(), pet.description()));
            vectorStore.add(List.of(petDocument));
        });
        return chatClient
                .defaultSystem(defaultSystemPrompt)
                .build();
    }

}
