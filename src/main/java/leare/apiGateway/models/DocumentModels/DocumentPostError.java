package leare.apiGateway.models.DocumentModels;


public class DocumentPostError {
    private Boolean success;
    private String error;
    public DocumentPostError(boolean success,String error) {

        this.success = success;
        this.error = error;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
 
    
}
