package leare.apiGateway.controllers.graphql;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import leare.apiGateway.models.AuthModels.Login;
import leare.apiGateway.models.AuthModels.RegisterInput;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.AuthModels.RouteRequest;


@Controller
public class AuthController {

    private final WebClient webClient;

    public AuthController() {
        this.webClient = WebClient.create("http://localhost:7202");
    }

    @QueryMapping
    public String getRoute(@Argument RouteRequest routeRequest, @Argument String token) {
        System.out.println("llega a query de ql");
        return webClient.post()
                        .uri("/Test/getRoute")
                        .bodyValue(routeRequest)
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @MutationMapping 
    public String login(@Argument Login login) {
        System.out.println("llega a query de ql");
        return webClient.post()
                        .uri("/api/Account/login")
                        .bodyValue(login)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @MutationMapping
    public RegisterResponse register(@Argument RegisterInput registerInput) {
        System.out.println("llega a query de ql");
        return webClient.post()
                        .uri("/api/Account/register")
                        .bodyValue(registerInput)
                        .retrieve()
                        .bodyToMono(RegisterResponse.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }


  
}
