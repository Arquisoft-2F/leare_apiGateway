package leare.apiGateway.models.ChatModels;

public class ChatUser {
    private String id;
    private String chat_id;
    private String user_id;
    private String user_nickname;
    private String user_is_admin;
    private String user_last_read;

    public ChatUser(String id, String chat_id, String user_id, String user_nickname, String user_is_admin, String user_last_read) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_is_admin = user_is_admin;
        this.user_last_read = user_last_read;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
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

    public String getUser_is_admin() {
        return user_is_admin;
    }

    public void setUser_is_admin(String user_is_admin) {
        this.user_is_admin = user_is_admin;
    }

    public String getUser_last_read() {
        return user_last_read;
    }
}
