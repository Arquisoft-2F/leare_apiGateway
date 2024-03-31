package leare.apiGateway.controllers.graphql;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import leare.apiGateway.controllers.consumers.AuthConsumer;
import leare.apiGateway.models.AuthModels.LoginResponse;
import leare.apiGateway.models.AuthModels.RegisterInput;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.AuthModels.LoginResponse;

@Controller
public class AuthResolver {

    private final WebClient webClient;
    private final AuthConsumer authConsumer;

    public AuthResolver() {
        // this.webClient = WebClient.create("http://localhost:5183");
        this.webClient = WebClient.create("http://auth-web:8080");
        this.authConsumer = new AuthConsumer();

    }


    @MutationMapping 
    public LoginResponse login(@Argument String email, @Argument String password) {
        return authConsumer.Login(email, password);
    }

    
}
