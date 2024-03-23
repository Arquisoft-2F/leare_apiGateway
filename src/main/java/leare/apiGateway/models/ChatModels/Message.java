package leare.apiGateway.models.ChatModels;

public class Message {
    private String sender_id;
    private String sender_nickname;
    private String content;
    private String created_at;
    private String updated_at;

    public Message(String sender_id, String sender_nickname, String content, String created_at, String updated_at) {
        this.sender_id = sender_id;
        this.sender_nickname = sender_nickname;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getSender_nickname() {
        return sender_nickname;
    }

    public void setSender_nickname(String sender_nickname) {
        this.sender_nickname = sender_nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

}
