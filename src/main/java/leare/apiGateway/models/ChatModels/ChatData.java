package leare.apiGateway.models.ChatModels;

public class ChatData {
    private String chat_id;
    private String chat_name;
    private String picture_id;
    private String created_at;
    private Message last_message;

    public ChatData(String chat_id, String chat_name, String picture_id, String created_at, Message last_message) {
        this.chat_id = chat_id;
        this.chat_name = chat_name;
        this.picture_id = picture_id;
        this.created_at = created_at;
        this.last_message = last_message;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Message getLast_message() {
        return last_message;
    }

    public void setLast_message(Message last_message) {
        this.last_message = last_message;
    }

}