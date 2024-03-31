package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Students;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;
import leare.apiGateway.models.UserModels.responses.UserResponse;
import leare.apiGateway.validation.UserValidation;

@Component
public class UserConsumer {
    
    private final WebClient usersClient;
    private final UserValidation userValidation;

    @Autowired
    public UserConsumer(WebClient.Builder webClientBuilder) {
        String url = "http://users-web";
        String port = "3000";
        this.usersClient = webClientBuilder.baseUrl(url + ":" + port).build();
        this.userValidation = new UserValidation();
    }

    public Users[] users() {
        return usersClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Users[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

    
    public Users userById(String id, String AuthorizationHeader) {
        Users user = usersClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Users.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        return user;
        
    }

    public Enrollment[] enrollements() {
        return usersClient.get()
                .uri("/courses_users")
                .retrieve()
                .bodyToMono(Enrollment[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    public Enrollment[] myCourses(String user_id) {
        return usersClient.get()
                .uri("/users/{user_id}/enroll", user_id)
                .retrieve()
                .bodyToMono(Enrollment[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    public Enrollment isEnrolled(String user_id, String course_id) {
        return usersClient.get()
                .uri("/users/{user_id}/enroll/{course_id}", user_id, course_id)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    public Students[] getCourses(String course_id) {
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

    public Users createUser(UsersInput user) {
        Users createdUser = usersClient.post()
                .uri("/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Users.class)
                .block();
        return createdUser;
    }

    public Users updateUser(UsersInput user, String id) {
        Users updatedUser = usersClient.patch()
                .uri("/users/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Users.class)
                .block();
        return updatedUser;
    }

    public Users deleteUser(String id) {
        Users deletedUser = usersClient.delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Users.class)
                .block();
        return deletedUser;
    }

    public Users updateMe(UsersInput user, String id) {
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
        return updatedUser;
    }

    public Users deleteMe(String id) {
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
        return deletedUser;
    }

    public Enrollment createEnrollment(String course_id, String user_id) {
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

    public Enrollment updateEnrollment(EnrollInput enrollment, String course_id,
            String user_id) {
        return usersClient.patch()
                .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                .bodyValue(enrollment)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block();
    }

    public Enrollment deleteEnrollment(String course_id, String user_id) {
        return usersClient.delete()
                .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                .retrieve()
                .bodyToMono(Enrollment.class)
                .block();
    }

}
