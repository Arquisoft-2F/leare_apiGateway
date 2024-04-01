package leare.apiGateway.models.DocumentModels;
import java.util.Objects;

public class DocumentPostSuccess {
    private Boolean success;
    public DocumentPostSuccess(boolean success) {
        this.success = success;
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
}
