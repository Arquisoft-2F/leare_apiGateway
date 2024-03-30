package leare.apiGateway.models.DocumentModels;
import java.util.Objects;

public class DocumentPost {
    private Boolean success;
    // private String error;
    public DocumentPost(boolean success) {
    // public DocumentPost(boolean success,String error) {
        this.success = success;
        // this.error = error;
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

    // public String getError() {
    //     return this.error;
    // }

    // public void setError(String error) {
    //     this.error = error;
    // }
 
    
}
