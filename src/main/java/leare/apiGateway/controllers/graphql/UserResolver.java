package leare.apiGateway.controllers.graphql;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Students;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.responses.UserResponse;
import leare.apiGateway.validation.UserValidation;
import reactor.core.publisher.Mono;
import leare.apiGateway.controllers.consumers.AuthConsumer;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.http.server.reactive.ServerHttpRequest;


// class RequestHeaderInterceptor implements WebGraphQlInterceptor { 

//     //It never enters here. THat is the problem
//     @Override
//     public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
//         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
//         System.out.println(request.getHeaders());
//         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
//         String value = request.getHeaders().getFirst("Authorization");
//         request.configureExecutionInput((executionInput, builder) ->
//                 builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());

                
//         return chain.next(request);
//     }
// }

// class RequestHeaderInterceptor implements WebGraphQlInterceptor { 

//     @Override
//     public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
//         System.out.println("7777777777777777777777777777");
//         System.out.println("7777777777777777777777777777");
//         System.out.println("7777777777777777777777777777");
        
//         String value = request.getHeaders().getFirst("Authorization");
//         System.out.println(value);
//         request.configureExecutionInput((executionInput, builder) ->
//                 builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
//         return chain.next(request);
//     }
// }

@Controller
public class UserResolver {

    private final WebClient usersClient;
    private final UserValidation userValidation;
    private final AuthConsumer auth;

    public UserResolver() {
        String url = "http://users-web";
        String port = "3000";
        this.usersClient = WebClient.create(url + ":" + port);
        this.userValidation = new UserValidation();
        this.auth = new AuthConsumer();
    }

    @QueryMapping
    public Users[] users() {
        return usersClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Users[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

    
    @QueryMapping
    public UserResponse userById(@Argument String id, @ContextValue("Authorization") String AuthorizationHeader) {
        
        
        // //String route, String method, String token
        // auth.Test();
        auth.CheckRoute("/user/:id","get",AuthorizationHeader);
        try {
            Users user = usersClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return userValidation.DeleteMe(user);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @QueryMapping
    public Enrollment[] enrollements() {
        return usersClient.get()
                .uri("/courses_users")
                .retrieve()
                .bodyToMono(Enrollment[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @QueryMapping
    public Enrollment[] myCourses(@Argument String user_id) {
        return usersClient.get()
                .uri("/users/{user_id}/enroll", user_id)
                .retrieve()
                .bodyToMono(Enrollment[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @QueryMapping
    public Enrollment isEnrolled(@Argument String user_id, @Argument String course_id) {
        return usersClient.get()
                .uri("/users/{user_id}/enroll/{course_id}", user_id, course_id)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @QueryMapping
    public Students[] getCourses(@Argument String course_id) {
        Users[] x = usersClient.get()
                .uri("/courses_users/{course_id}/users", course_id)
                .retrieve()
                .bodyToMono(Users[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        Enrollment[] y = usersClient.get()
                .uri("/courses_users/{course_id}/users", course_id)
                .retrieve()
                .bodyToMono(Enrollment[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        Students[] result = new Students[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = new Students(x[i], y[i]);
        }
        return result;
    }

    @MutationMapping
    public UserResponse createUser(@Argument UsersInput user) {
        try {
            Users createdUser = usersClient.post()
                    .uri("/users")
                    .bodyValue(user)
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();
            return userValidation.DeleteMe(createdUser);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @MutationMapping
    public UserResponse updateUser(@Argument UsersInput user, @Argument String id) {
        try {
            Users updatedUser = usersClient.patch()
                    .uri("/users/{id}", id)
                    .bodyValue(user)
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();
            return userValidation.DeleteMe(updatedUser);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @MutationMapping
    public UserResponse deleteUser(@Argument String id) {
        try {
            Users deletedUser = usersClient.delete()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();

            return userValidation.DeleteMe(deletedUser);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @MutationMapping
    public UserResponse updateMe(@Argument UsersInput user, @Argument String id) {
        try {
            Users updatedUser = ((RequestBodySpec) usersClient.patch()
                    .uri("/users/me")
                    .bodyValue(user))
                    .bodyValue(new HashMap<String, String>() {
                        {
                            put("id", id);
                        }
                    })
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();
            return userValidation.DeleteMe(updatedUser);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @MutationMapping
    public UserResponse deleteMe(@Argument String id) {
        try {

            Users deletedUser = ((RequestBodySpec) usersClient.delete()
                    .uri("/users/me"))
                    .bodyValue(new HashMap<String, String>() {
                        {
                            put("id", id);
                        }
                    })
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();

            return userValidation.DeleteMe(deletedUser);
        } catch (WebClientResponseException ex) {
            return userValidation.UserClientEx(ex);
        } catch (Exception ex) {
            return userValidation.UserEx(ex);
        }
    }

    @MutationMapping
    public Enrollment createEnrollment(@Argument String course_id, @Argument String user_id) {
        return usersClient.post()
                .uri("/courses_users")
                .bodyValue(new HashMap<String, String>() {
                    {
                        put("course_id", course_id);
                        put("user_id", user_id);
                    }
                })
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block();
    }

    @MutationMapping
    public Enrollment updateEnrollment(@Argument EnrollInput enrollment, @Argument String course_id,
            @Argument String user_id) {
        return usersClient.patch()
                .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                .bodyValue(enrollment)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block();
    }

    @MutationMapping
    public Enrollment deleteEnrollment(@Argument String course_id, @Argument String user_id) {
        return usersClient.delete()
                .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block();
    }
}
