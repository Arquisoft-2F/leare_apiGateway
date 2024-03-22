package leare.apiGateway.models.ChatModels;

public class Chat {
    private String user_id;
    private String user_nickname;
    private boolean user_is_admin;
    private String user_last_read;
    private ChatData chat;

    public Chat(String user_id, String user_nickname, boolean user_is_admin, String user_last_read, ChatData chat) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_is_admin = user_is_admin;
        this.user_last_read = user_last_read;
        this.chat = chat;
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

    public boolean isUser_is_admin() {
        return user_is_admin;
    }

    public void setUser_is_admin(boolean user_is_admin) {
        this.user_is_admin = user_is_admin;
    }

    public String getUser_last_read() {
        return user_last_read;
    }

    public void setUser_last_read(String user_last_read) {
        this.user_last_read = user_last_read;
    }

    public ChatData getChat() {
        return chat;
    }

    public void setChat(ChatData chat) {
        this.chat = chat;
    }
}