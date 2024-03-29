package leare.apiGateway.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import leare.apiGateway.models.UserModels.Users;

import leare.apiGateway.models.UserModels.responses.UserResponse;

public class UserValidation {

    public UserResponse DeleteMe(Users deletedUser) {
        if (deletedUser != null) {
            return new UserResponse(deletedUser, null);
        } else {
            return new UserResponse(null, "User not found");
        }
     
    }
    public UserResponse UserClientEx(WebClientResponseException ex){
        // System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // System.err.println(ex.getRawStatusCode()>299);
        // System.err.println(ex.getStatusText());
        // System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new UserResponse(null, "User not found");
        } else if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
            return new UserResponse(null, "Access denied");
        } else {
            return new UserResponse(null, ex.getMessage());
        }

    }
    public UserResponse UserEx(Exception ex){
        return new UserResponse(null, "Unexpected error: " + ex.getMessage());
    }
}
