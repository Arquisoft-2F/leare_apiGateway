package leare.apiGateway.controllers.consumers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import leare.apiGateway.models.ChatModels.Chat;
import leare.apiGateway.models.ChatModels.ChatData;
import leare.apiGateway.models.ChatModels.ChatInput;
import leare.apiGateway.models.ChatModels.ChatUser;
import leare.apiGateway.models.ChatModels.Message;
import reactor.core.publisher.Mono;

@Component
public class ChatConsumer {
    private final WebClient webClient;

    @Autowired
    public ChatConsumer(WebClient.Builder webClientBuilder) {
        String baseUrl = "http://chat-web";
        String port = "3002";
        String prefix = "/chat";
        this.webClient = webClientBuilder.baseUrl(baseUrl + ":" + port + prefix).build();
    }

    public Chat[] getUserChats(String userId) {
        return webClient
                .get()
                .uri("/user/{userId}", userId)
                .retrieve()
                .bodyToMono(Chat[].class)
                .block();
    }

    public Message[] getChatMessages(String chatId, String userId) {
        return webClient
                .get()
                .uri("/{chatId}/messages?user_id={userId}", chatId, userId)
                .retrieve()
                .bodyToMono(Message[].class)
                .block();
    }

    public ChatData createChat(ChatInput chatInput, String user_id, String user_nickname) {

        return webClient
                .post()
                .uri("/?user_id={user_id}&user_nickname={user_nickname}", user_id, user_nickname)
                .bodyValue(chatInput)
                .retrieve()
                .bodyToMono(ChatData.class)
                .block();
    }

    public ChatUser joinChat(String user_id, String user_nickname, String chatId) {
        return webClient
                .patch()
                .uri("/{chatId}/join?user_id={userId}&user_nickname={userNickname}",
                        chatId, user_id, user_nickname)
                .retrieve()
                .bodyToMono(ChatUser.class)
                .block();
    }

    public Mono<Void> leaveChat(String chatId, String userId) {
        return webClient
                .patch()
                .uri("/{chatId}/leave?user_id={userId}", chatId, userId)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> deleteChat(String chatId, String user_id) {
        return webClient
                .delete()
                .uri("/{chatId}?user_id={userId}", chatId, user_id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
