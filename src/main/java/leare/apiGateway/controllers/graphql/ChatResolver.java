package leare.apiGateway.controllers.graphql;

import java.util.HashMap;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.ChatModels.Chat;
import leare.apiGateway.models.ChatModels.ChatData;
import leare.apiGateway.models.ChatModels.ChatInput;
import leare.apiGateway.models.ChatModels.ChatJoinInput;
import leare.apiGateway.models.ChatModels.ChatUser;
import leare.apiGateway.models.ChatModels.Message;

@Controller
public class ChatResolver {
    private final WebClient webClient;

    public ChatResolver() {
        String url = "http://chat-web";
        String port = "3002";
        String prefix = "/chat";
        this.webClient = WebClient.create(url + ":" + port + prefix);
    }

    // Get user chats
    @QueryMapping
    public Chat[] userChats(@Argument String user_id) {
        return webClient
            .get()
            .uri("/user/{user_id}", user_id)
            .retrieve()
            .bodyToMono(Chat[].class)
            .block();
    }

    // Get chat messages
    @QueryMapping
    public Message[] chatMessages(@Argument String chat_id, @Argument String user_id) {
        return webClient
            .get()
            .uri("/{chat_id}/messages?user_id={user_id}", chat_id, user_id)
            .retrieve()
            .bodyToMono(Message[].class)
            .block();
    }

    // Create chat
    @MutationMapping
    public ChatData createChat(@Argument ChatInput chat_input) {
        ChatData chat = webClient
            .post()
            .uri("/")
            .bodyValue(chat_input)
            .retrieve()
            .bodyToMono(ChatData.class)
            .block();

        return chat;
    }

    // Join chat
    @MutationMapping
    public ChatUser joinChat(@Argument ChatJoinInput chat_join_input) {
        ChatUser chat = webClient
            .patch()
            .uri("/{chat_id}/join?user_id={user_id}&user_nickname={user_nickname}", chat_join_input.getChat_id(), chat_join_input.getUser_id(), chat_join_input.getUser_nickname())
            .retrieve()
            .bodyToMono(ChatUser.class)
            .block();

        return chat;
    }

    // Leave chat
    @MutationMapping
    public Map<String, String> leaveChat(@Argument String chat_id, @Argument String user_id) {
        webClient
            .patch()
            .uri("/{chat_id}/leave?user_id={user_id}", chat_id, user_id)
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        Map<String, String> response = new HashMap<>();
        response.put("message", "User left chat");

        return response;
    }

    // Delete chat
    @MutationMapping
    public Map<String, String> deleteChat(@Argument String chat_id) {
        webClient
            .delete()
            .uri("/{chat_id}", chat_id)
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        Map<String, String> response = new HashMap<>();
        response.put("message", "Chat deleted");

        return response;
    }

}