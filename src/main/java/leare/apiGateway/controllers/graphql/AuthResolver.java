package leare.apiGateway.controllers.graphql;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import leare.apiGateway.models.AuthModels.LoginResponse;
import leare.apiGateway.models.AuthModels.RegisterInput;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.AuthModels.LoginResponse;

@Controller
public class AuthResolver { // TODO: ELIMINAR ESTO O EN SU DEFECTO DEJAR SOLO EL LOGIN EL CUAL DEBERIA USAR EL CONSUMER

    private final WebClient webClient;

    public AuthResolver() {
        // this.webClient = WebClient.create("http://localhost:5183");
        this.webClient = WebClient.create("http://auth-web:8080");
    }

    @QueryMapping
    public String getRoute(@Argument String route, @Argument String method, @Argument String token) {
        return webClient.post()
                        .uri("/Test/getRoute")
                        .bodyValue(new HashMap<String, String>() {{
                            put("route", route);
                            put("method", method);}})
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @MutationMapping 
    public LoginResponse login(@Argument String email, @Argument String password) {
        return webClient.post()
                        .uri("/api/Account/login")
                        .bodyValue(new HashMap<String, String>() {{
                            put("email", email);
                            put("password", password);}})
                        .retrieve()
                        .bodyToMono(LoginResponse.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @MutationMapping
    public RegisterResponse register(@Argument RegisterInput registerInput) {
        return webClient.post()
                        .uri("/api/Account/register")
                        .bodyValue(registerInput)
                        .retrieve()
                        .bodyToMono(RegisterResponse.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }


  
}
