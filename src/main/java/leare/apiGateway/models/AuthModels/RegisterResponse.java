package leare.apiGateway.models.AuthModels;
import java.util.Objects;

public class RegisterResponse {
    private String flag;
    private String message;
    private String token;


    public RegisterResponse() {
    }

    public RegisterResponse(String flag, String message, String token) {
        this.flag = flag;
        this.message = message;
        this.token = token;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterResponse flag(String flag) {
        setFlag(flag);
        return this;
    }

    public RegisterResponse message(String message) {
        setMessage(message);
        return this;
    }

    public RegisterResponse token(String token) {
        setToken(token);
        return this;
    }
    
}
