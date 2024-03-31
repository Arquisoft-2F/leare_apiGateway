package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.AuthModels.LoginResponse;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.AuthModels.DecryptedToken;
import leare.apiGateway.validation.UserValidation;

@Component
public class AuthConsumer {
    private final WebClient AuthClient;

    @Autowired
    public AuthConsumer(WebClient.Builder webClientBuilder) {
        this.AuthClient = webClientBuilder.baseUrl("http://auth-web:8080").build();
    }
    public LoginResponse Login(String email,String password){
        return AuthClient.post()
                        .uri("/api/Account/login")
                        .bodyValue(new HashMap<String, String>() {{
                            put("email", email);
                            put("password", password);}})
                        .retrieve()
                        .bodyToMono(LoginResponse.class)
                        .block();

    }
    public RegisterResponse Register(String name, String email, String password, String confirmPassword, String role,String userId){
        return AuthClient.post()
                        .uri("/api/Account/register")
                        .bodyValue(new HashMap<String, String>() {{
                            put("name", name);
                            put("email", email);
                            put("password", password);
                            put("confirmPassword", confirmPassword);
                            put("role", role);
                            put("userId", userId);
                        }})
                        .retrieve()
                        .bodyToMono(RegisterResponse.class)
                        .block();
    }

    public Boolean CheckRoute(String route, String method, String token){
        String RouteResponse = AuthClient.post()
                        .uri("/Test/getRoute")
                        .bodyValue(new HashMap<String, String>() {{
                            put("route", route);
                            put("method", method);}})
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        if (RouteResponse.equals("Authorized")) {
        return true;
        }
        return false;
    }

    public DecryptedToken DecryptToken(String token){
        return AuthClient.get()
                        .uri("/decodeJwt")
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(DecryptedToken.class)
                        .block();
    }
}
