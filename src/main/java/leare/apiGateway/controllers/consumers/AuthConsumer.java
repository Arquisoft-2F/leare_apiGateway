package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.validation.UserValidation;

@Component
public class AuthConsumer {
    private final WebClient AuthClient;

    @Autowired
    public AuthConsumer(WebClient.Builder webClientBuilder) {
        this.AuthClient = webClientBuilder.baseUrl("http://auth-web:8080").build();
    }
    public void Login(){

    }
    public void Register(){
        
    }

    public String CheckRoute(String route, String method, String token){
        return AuthClient.post()
                        .uri("/Test/getRoute")
                        .bodyValue(new HashMap<String, String>() {{
                            put("route", route);
                            put("method", method);}})
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }
}
