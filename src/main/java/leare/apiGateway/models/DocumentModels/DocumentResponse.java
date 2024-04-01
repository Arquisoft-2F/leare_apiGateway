package leare.apiGateway.models.DocumentModels;

import java.util.UUID;

public class DocumentResponse {
    private Boolean success;
    private UUID uuid;

    public DocumentResponse(Boolean success, UUID uuid) {
        this.success = success;
        this.uuid = uuid;
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

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
