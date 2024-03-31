package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

public class UserConsumer {
    
    private final WebClient usersClient;
    private final UserValidation userValidation;
    private final AuthConsumer auth;
    private final SearchConsumer search;

    public UserConsumer() {
        String url = "http://users-web";
        String port = "3000";
        this.usersClient = WebClient.create(url + ":" + port);
        this.userValidation = new UserValidation();
        this.auth = new AuthConsumer();
        this.search = new SearchConsumer();
    }

    public Users[] users() {
        return usersClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Users[].class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

    
    public UserResponse userById(String id, String AuthorizationHeader) {
        //!search

        // UUID x = UUID.randomUUID();
        // if( search.AddCourseIndex(x.toString(), "2", "3", "4") == false){
        // }
        // search.UpdateCourseIndex(x.toString(), "update", "update", "update");
        // x = UUID.randomUUID();
        // if( search.AddUsersIndex(x.toString(), "2", "3", "4","5") == false){
        //     //do something with the error?
        // }
        // search.UpdateUsersIndex(x.toString(), "update", "update", "update","update");
        // x = UUID.randomUUID();
        // if( search.AddCategoryIndex(x.toString(), "2") == false){
        //     //do something with the error?
        // }
        // search.UpdateCategoryIndex(x.toString(), "AQUI ESTOY");
        // search.DeleteIndex(x.toString());

        //!auth
        // //String route, String method, String token
        // auth.CheckRoute("/user/:id","get",AuthorizationHeader);

        //!document
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

    public UserResponse updateUser(UsersInput user, String id) {
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

    public UserResponse deleteUser(String id) {
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

    public UserResponse updateMe(UsersInput user, String id) {
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

    public UserResponse deleteMe(String id) {
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
