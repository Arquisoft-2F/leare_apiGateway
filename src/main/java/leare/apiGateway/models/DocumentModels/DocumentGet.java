package leare.apiGateway.models.DocumentModels;


public class DocumentGet {

    private boolean success;
    private DocumentGetValue value;

    public DocumentGet(boolean success, DocumentGetValue value) {
        this.success = success;
        this.value = value;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DocumentGetValue getValue() {
        return this.value;
    }

    public void setValue(DocumentGetValue value) {
        this.value = value;
    }

    public DocumentGet success(boolean success) {
        setSuccess(success);
        return this;
    }

    public DocumentGet value(DocumentGetValue value) {
        setValue(value);
        return this;
    }

}
