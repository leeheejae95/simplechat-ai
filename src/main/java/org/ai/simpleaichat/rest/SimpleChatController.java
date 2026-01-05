package org.ai.simpleaichat.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @GetMapping(value = "call", produces = MediaType.APPLICATION_JSON_VALUE)
    ChatResponse call(String userPrompts) {
        return this.chatClient.prompt().user(userPrompts).call().chatResponse();
    }

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> stream(String userPrompts) {
        return this.chatClient.prompt().user(userPrompts).stream().content();
    }
}
