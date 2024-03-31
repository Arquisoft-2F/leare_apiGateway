package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Students;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;
import leare.apiGateway.models.UserModels.responses.UserResponse;
import leare.apiGateway.validation.UserValidation;

@Component
public class DocumentConsumer {
    
    private final WebClient documentClient;

    @Autowired
    public DocumentConsumer(WebClient.Builder webClientBuilder) {
        String url = "http://document-server";
        String port = "3004";
        this.documentClient = webClientBuilder.baseUrl(url + ":" + port).build();
    }

    public String getDocument( String id) {
        return documentClient.get()
                .uri("/read/{id}",id)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

    public String deleteDocument(String id) {
        return documentClient.delete()
                .uri("/read/{id}",id)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

}
