package com.shri.spring.springaiaws.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class AdoptionController {

    private final ChatClient chatClient;

    @GetMapping("/{user}/inquire")
    public String inquire(@PathVariable(name = "user") String user,
                           @RequestParam String question) {
        return this.chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

}
