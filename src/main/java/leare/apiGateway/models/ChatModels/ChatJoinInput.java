package leare.apiGateway.models.ChatModels;

public class ChatJoinInput {
    private String user_id;
    private String user_nickname;
    private String chat_id;

    public ChatJoinInput(String user_id, String user_nickname, String chat_id) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.chat_id = chat_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

}
