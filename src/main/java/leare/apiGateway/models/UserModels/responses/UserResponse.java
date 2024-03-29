package leare.apiGateway.models.UserModels.responses;

import leare.apiGateway.models.UserModels.Users;

public class UserResponse {
    private Users data;
    private String error;

    public UserResponse() {
    }

    public UserResponse(Users data, String error) {
        this.data = data;
        this.error = error;
    }

    // Getters and setters
    public Users getUsers() {
        return data;
    }

    public void setUsers(Users data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
