package leare.apiGateway.models.UserModels.responses;
import leare.apiGateway.models.UserModels.Users;

public class CreateResponse {
    private Users users;
    private String token;


    public CreateResponse(Users users, String token) {
        this.users = users;
        this.token = token;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
