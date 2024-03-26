package leare.apiGateway.models.ChatModels;

public class ChatInput {
    private String chat_name;

    public ChatInput(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getchat_name() {
        return chat_name;
    }

    public void setchat_name(String chat_name) {
        this.chat_name = chat_name;
    }

}
