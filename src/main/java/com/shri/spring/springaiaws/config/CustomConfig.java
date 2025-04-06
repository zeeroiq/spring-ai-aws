package com.shri.spring.springaiaws.config;

import com.shri.spring.springaiaws.repository.PetRepository;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
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
                                    VectorStore vectorStore,
                                    McpSyncClient mcpSyncClient) {

        petRepository.findAll().forEach(pet -> {
            Document petDocument = new Document("id: %s, name: %s, description: %s".formatted(pet.id(), pet.name(), pet.description()));
            vectorStore.add(List.of(petDocument));
        });
        return chatClient
                .defaultSystem(defaultSystemPrompt)
                .defaultTools(new SyncMcpToolCallbackProvider(mcpSyncClient))
                .build();
    }

    @Bean
    protected QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        return new QuestionAnswerAdvisor(vectorStore);
    }

    @Bean
    protected McpSyncClient mcpSyncClient() {
        var mcp = McpClient
                .sync(new HttpClientSseClientTransport("http://localhost:8081"))
                .build();
        mcp.initialize();
        return mcp;
    }

}
