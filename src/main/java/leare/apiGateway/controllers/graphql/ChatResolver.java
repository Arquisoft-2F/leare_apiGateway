package leare.apiGateway.controllers.graphql;
import leare.apiGateway.controllers.consumers.ChatConsumer;
import leare.apiGateway.models.ChatModels.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatResolver {
    private final ChatConsumer chatConsumer;

    @Autowired
    public ChatResolver() {
        this.chatConsumer = new ChatConsumer();
    }

    @QueryMapping
    public Chat[] userChats(@Argument String user_id) {
        return chatConsumer.getUserChats(user_id);
    }

    @QueryMapping
    public Message[] chatMessages(@Argument String chat_id, @Argument String user_id) {
        return chatConsumer.getChatMessages(chat_id, user_id);
    }

    @MutationMapping
    public ChatData createChat(@Argument ChatInput chat_input) {
        return chatConsumer.createChat(chat_input);
    }

    @MutationMapping
    public ChatUser joinChat(@Argument ChatJoinInput chat_join_input) {
        return chatConsumer.joinChat(chat_join_input);
    }

    @MutationMapping
    public Map<String, String> leaveChat(@Argument String chat_id, @Argument String user_id) {
        chatConsumer.leaveChat(chat_id, user_id).block();
        Map<String, String> response = new HashMap<>();
        response.put("message", "User left chat");
        return response;
    }

    @MutationMapping
    public Map<String, String> deleteChat(@Argument String chat_id) {
        chatConsumer.deleteChat(chat_id).block();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Chat deleted");
        return response;
    }
}