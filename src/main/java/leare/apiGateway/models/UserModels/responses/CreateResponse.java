package leare.apiGateway.models.UserModels.responses;
import leare.apiGateway.models.UserModels.Users;

public class CreateResponse {
    private Users user;
    private String token;


    public CreateResponse(Users user, String token) {
        this.user = user;
        this.token = token;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CreateResponse user(Users user) {
        setUser(user);
        return this;
    }

    public CreateResponse token(String token) {
        setToken(token);
        return this;
    }

}
