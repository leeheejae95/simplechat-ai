package org.ai.simpleaichat.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleChatController {

    public final ChatClient chatClient;

    public SimpleChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("ai")
    String generation(String userPrompts) {
        return this.chatClient.prompt().user(userPrompts).call().content();
    }
}
