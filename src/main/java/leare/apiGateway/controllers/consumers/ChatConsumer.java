package leare.apiGateway.controllers.consumers;

import org.springframework.web.reactive.function.client.WebClient;
import leare.apiGateway.models.ChatModels.Chat;
import leare.apiGateway.models.ChatModels.ChatData;
import leare.apiGateway.models.ChatModels.ChatInput;
import leare.apiGateway.models.ChatModels.ChatJoinInput;
import leare.apiGateway.models.ChatModels.ChatUser;
import leare.apiGateway.models.ChatModels.Message;
import reactor.core.publisher.Mono;

public class ChatConsumer {
    private final WebClient webClient;

    public ChatConsumer() {
        String baseUrl = "http://chat-web";
        String port = "3002";
        String prefix = "/chat";
        this.webClient = WebClient.create(baseUrl + ":" + port + prefix);
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

    public ChatData createChat(ChatInput chatInput) {
        return webClient
                .post()
                .uri("/")
                .bodyValue(chatInput)
                .retrieve()
                .bodyToMono(ChatData.class)
                .block();
    }

    public ChatUser joinChat(ChatJoinInput chatJoinInput) {
        return webClient
                .patch()
                .uri("/{chatId}/join?user_id={userId}&user_nickname={userNickname}",
                        chatJoinInput.getChat_id(), chatJoinInput.getUser_id(), chatJoinInput.getUser_nickname())
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

    public Mono<Void> deleteChat(String chatId) {
        return webClient
                .delete()
                .uri("/{chatId}", chatId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
