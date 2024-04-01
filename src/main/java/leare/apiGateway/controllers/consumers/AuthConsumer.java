package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.AuthModels.LoginResponse;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.AuthModels.DecryptedToken;
import leare.apiGateway.validation.UserValidation;
import com.google.gson.Gson;

@Component
public class AuthConsumer {
    private final WebClient AuthClient;

    @Autowired
    public AuthConsumer(WebClient.Builder webClientBuilder) {
        this.AuthClient = webClientBuilder.baseUrl("http://auth-web:8080").build();
    }
    public LoginResponse Login(String username,String password){
        return AuthClient.post()
                        .uri("/api/Account/login")
                        .bodyValue(new HashMap<String, String>() {{
                            put("username", username);
                            put("password", password);}})
                        .retrieve()
                        .bodyToMono(LoginResponse.class)
                        .block();

    }
    public RegisterResponse Register(String name, String username, String email, String password, String confirmPassword, String role,String userId){
        return AuthClient.post()
                        .uri("/api/Account/register")
                        .bodyValue(new HashMap<String, String>() {{
                            put("name", name);
                            put("username", username);
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

    public RegisterResponse updateAuth(String name, String username, String email, String password, String confirmPassword, String role,String userId){
        return AuthClient.put()
                        .uri("/api/Account/edit/{userId}",userId)
                        .bodyValue(new HashMap<String, String>() {{
                            put("name", name);
                            put("username", username);
                            put("email", email);
                            put("password", "password");
                            put("confirmPassword", "password");
                            put("role", "0");
                            put("userId", userId);
                        }})
                        .retrieve()
                        .bodyToMono(RegisterResponse.class)
                        .block();
    }

    public RegisterResponse deleteAuth(String userId){
        return AuthClient.delete()
                        .uri("/api/Account/delete/{userId}",userId)
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
        String rawDecryptedToken = AuthClient.get()
                        .uri("/decodeJwt")
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
        Gson gson = new Gson();
        return gson.fromJson(rawDecryptedToken, DecryptedToken.class);
    }
}
