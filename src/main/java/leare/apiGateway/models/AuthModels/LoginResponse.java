package leare.apiGateway.models.AuthModels;

public class LoginResponse {
    private String flag;
    private String token;
    private String message;

    public LoginResponse(String flag, String token, String message) {
        this.flag = flag;
        this.token = token;
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
