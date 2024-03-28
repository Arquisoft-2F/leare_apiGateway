package leare.apiGateway.models.AuthModels;

public class RegisterResponse {
    private String flag;
    private String message;

    public RegisterResponse(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
