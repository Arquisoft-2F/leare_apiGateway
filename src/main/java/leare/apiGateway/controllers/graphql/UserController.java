package leare.apiGateway.controllers.graphql;

import java.util.HashMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;

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

    @QueryMapping
    public Enrollment[] enrollements() {
        System.out.println("llega a query de ql");
        return webClient.get()
                        .uri("/courses_users")
                        .retrieve()
                        .bodyToMono(Enrollment[].class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
    }

    @QueryMapping
    public Enrollment isEnrolled(@Argument String user_id, @Argument String course_id) {
        System.out.println("llega a query de ql");
        return webClient.get()
                        .uri("/users/{user_id}/enroll/{course_id}", user_id, course_id)
                        .retrieve()
                        .bodyToMono(Enrollment.class)
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

    @MutationMapping
    public Enrollment createEnrollment(@Argument String course_id, @Argument String user_id) {
            return webClient.post()
                        .uri("/courses_users")
                        .bodyValue(new HashMap<String, String>() {{
                            put("course_id", course_id);
                            put("user_id", user_id);}})
                        .retrieve()
                        .bodyToMono(Enrollment.class)
                        .block();
    }

    @MutationMapping
    public Enrollment updateEnrollment(@Argument EnrollInput enrollment, @Argument String course_id, @Argument String user_id) {
            return webClient.patch()
                        .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                        .bodyValue(enrollment)
                        .retrieve()
                        .bodyToMono(Enrollment.class)
                        .block();
    }

    @MutationMapping
    public Enrollment deleteEnrollment(@Argument String course_id, @Argument String user_id) {
            return webClient.delete()
                        .uri("/courses_users/{course_id}/{user_id}", course_id, user_id)
                        .retrieve()
                        .bodyToMono(Enrollment.class)
                        .block();
    }
}
