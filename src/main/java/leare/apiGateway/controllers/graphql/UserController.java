package leare.apiGateway.controllers.graphql;

import leare.apiGateway.models.Users;
import leare.apiGateway.models.UsersInput;

import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class UserController {

    private final WebClient webClient;

    public UserController() {
        this.webClient = WebClient.create("http://localhost:3001");
    }

    @QueryMapping
    public Users[] users() {
        System.out.println("llega a query de ql");
        return webClient.get()
                        .uri("/users")
                        .retrieve()
                        .bodyToMono(Users[].class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @QueryMapping
    public Users userById(@Argument String id) {
        System.out.println("llega a query de ql");
        return webClient.get()
                        .uri("/users/{id}", id)
                        .retrieve()
                        .bodyToMono(Users.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @MutationMapping
    public Users createUser(@Argument UsersInput user) {
            return webClient.post()
                        .uri("/users")
                        .bodyValue(user)
                        .retrieve()
                        .bodyToMono(Users.class)
                        .block();
    }

    @MutationMapping
    public Users updateUser(@Argument UsersInput user, @Argument String id) {
            return webClient.patch()
                        .uri("/users/{id}", id)
                        .bodyValue(user)
                        .retrieve()
                        .bodyToMono(Users.class)
                        .block();
    }

    @MutationMapping
    public Users deleteUser(@Argument String id) {
            return webClient.delete()
                        .uri("/users/{id}", id)
                        .retrieve()
                        .bodyToMono(Users.class)
                        .block();
    }
}
