package leare.apiGateway.controllers.graphql;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.ChatModels.Chat;

@Controller
public class ChatController {
    private final WebClient webClient;

    public ChatController() {
        this.webClient = WebClient.create("http://localhost:3002");
    }

    @QueryMapping
    public Chat[] userChats(@Argument String user_id) {
        return webClient.get()
                        .uri("/chat/user/{user_id}", user_id)
                        .retrieve()
                        .bodyToMono(Chat[].class)
                        .block();
    }
}