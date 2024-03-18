package leare.apiGateway.controllers.graphql;

import leare.apiGateway.models.Users;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class UserController {

    private final WebClient webClient;

    public UserController() {
        this.webClient = WebClient.create("http://localhost:3012");
    }

    @QueryMapping
    public Users userById(@Argument Long id) {
        System.out.println("llega a query de ql");
        return webClient.get()
                        .uri("/users/{id}", id)
                        .retrieve()
                        .bodyToMono(Users.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }
}
