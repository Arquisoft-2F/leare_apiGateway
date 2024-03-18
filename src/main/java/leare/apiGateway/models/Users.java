package leare.apiGateway.models;

public class Users extends UsersInput{
    private String id;

    // Constructor, getters, and setters
    public Users(String id, String nickname, String name, String lastname, String email, String nationality) {
        super(nickname, name, lastname, email, nationality);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}