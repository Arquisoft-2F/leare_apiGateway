package leare.apiGateway.controllers.graphql;
import leare.apiGateway.controllers.consumers.AuthConsumer;
import leare.apiGateway.controllers.consumers.ChatConsumer;
import leare.apiGateway.errors.AuthError;
import leare.apiGateway.models.AuthModels.DecryptedToken;
import leare.apiGateway.models.ChatModels.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatResolver {
    private final ChatConsumer chatConsumer;
    private final AuthConsumer authConsumer;

    @Autowired
    public ChatResolver(ChatConsumer chatConsumer, AuthConsumer authConsumer) {
        this.chatConsumer = chatConsumer;
        this.authConsumer = authConsumer;
    }

    @QueryMapping
    public Chat[] userChats(@ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        if (!authConsumer.CheckRoute("/chat/user/:user_id", "get", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        
        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);
        String user_id = decryptedToken.getUserID();
        return chatConsumer.getUserChats(user_id);
    }


    @QueryMapping
    public Message[] chatMessages(@ContextValue("Authorization") String AuthorizationHeader , @Argument String chat_id) throws Exception {

        if (!authConsumer.CheckRoute("/chat/:chat_id/messages", "get", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);
        String user_id = decryptedToken.getUserID();

        return chatConsumer.getChatMessages(chat_id, user_id);
    }

    @MutationMapping
    public ChatData createChat(@ContextValue("Authorization") String AuthorizationHeader, @Argument ChatInput chat_input) throws Exception {

        if (!authConsumer.CheckRoute("/chat", "post", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);

        String user_id = decryptedToken.getUserID();
        String user_nickname = decryptedToken.getUsername();

        return chatConsumer.createChat(chat_input, user_id, user_nickname);
    }

    @MutationMapping
    public ChatUser joinChat(@ContextValue("Authorization") String AuthorizationHeader,  @Argument String chat_id) throws Exception {

        if (!authConsumer.CheckRoute("/chat/:chat_id/join", "patch", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);

        String user_id = decryptedToken.getUserID();
        String user_nickname = decryptedToken.getUsername();
        return chatConsumer.joinChat(user_id, user_nickname, chat_id);
    }

    @MutationMapping
    public Map<String, String> leaveChat(@ContextValue("Authorization") String AuthorizationHeader, @Argument String chat_id) throws Exception {

        if (!authConsumer.CheckRoute("/chat/:chat_id/leave", "patch", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);

        String user_id = decryptedToken.getUserID();
        chatConsumer.leaveChat(chat_id, user_id).block();
        Map<String, String> response = new HashMap<>();
        response.put("message", "User left chat");
        return response;
    }

    @MutationMapping
    public Map<String, String> deleteChat(@ContextValue("Authorization") String AuthorizationHeader, @Argument String chat_id) throws Exception {

        if (!authConsumer.CheckRoute("/chat/chat:id", "delete", AuthorizationHeader)) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        DecryptedToken decryptedToken = authConsumer.DecryptToken(AuthorizationHeader);
        
        String user_id = decryptedToken.getUserID();

        chatConsumer.deleteChat(chat_id, user_id).block();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Chat deleted");
        return response;
    }
}