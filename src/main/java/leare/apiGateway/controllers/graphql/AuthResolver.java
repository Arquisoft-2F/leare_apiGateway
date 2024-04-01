package leare.apiGateway.controllers.graphql;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuthConsumer authConsumer;

    @Autowired
    public AuthResolver(AuthConsumer authConsumer) {
        this.authConsumer = authConsumer;
    }


    @MutationMapping 
    public LoginResponse login(@Argument String email, @Argument String password) {
        return authConsumer.Login(email, password);
    }

    @MutationMapping 
    public LoginResponse editPassword(@Argument String id, @Argument String OldPassword, @Argument String NewPassword) throws Exception {
        LoginResponse res = authConsumer.changePassword(id, OldPassword, NewPassword);
        if(res.getFlag().equals("false")){
            throw new Exception("Auth Problem");
        }
        return res;
    }

    
}
