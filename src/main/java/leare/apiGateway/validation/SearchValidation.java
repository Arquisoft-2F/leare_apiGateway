package leare.apiGateway.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import leare.apiGateway.models.SearchModels.ResponsePost;
import leare.apiGateway.models.UserModels.responses.UserResponse;

public class SearchValidation {
    
    public ResponsePost SearchClientEx(WebClientResponseException ex){
        // System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // System.err.println(ex.getRawStatusCode()>299);
        // System.err.println(ex.getStatusText());
        // System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponsePost(null,null, "Query not found");
        } else if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
            return new ResponsePost(null,null, "Access denied");
        } else {
            return new ResponsePost(null,null, ex.getMessage());
        }

    }

}
