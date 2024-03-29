package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.validation.UserValidation;

public class AuthConsumer {
    private final WebClient AuthClient;

    public AuthConsumer(){
        this.AuthClient = WebClient.create("http://auth-web:8080");
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
