package leare.apiGateway.models.UserModels.responses;

public class successMessage {
    private String success;
    private String message; 

    public successMessage(String success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    
}
