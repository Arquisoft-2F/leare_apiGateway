package leare.apiGateway.controllers.graphql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Students;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;

import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.responses.CreateResponse;
import leare.apiGateway.models.UserModels.responses.UserResponse;
import leare.apiGateway.validation.UserValidation;
import reactor.core.publisher.Mono;
import leare.apiGateway.controllers.consumers.AuthConsumer;
import leare.apiGateway.controllers.consumers.CourseConsumer;
import leare.apiGateway.controllers.consumers.DocumentConsumer;
import leare.apiGateway.controllers.consumers.SearchConsumer;
import leare.apiGateway.controllers.consumers.UserConsumer;
import graphql.language.Document;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.constraints.PastOrPresent;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final UserConsumer userConsumer;
    private final DocumentConsumer documentConsumer;
    private final SearchConsumer searchConsumer;
    private final AuthConsumer authConsumer;
    
    @Autowired
    public UserResolver() {
        this.userConsumer = new UserConsumer();
        this.documentConsumer = new DocumentConsumer();
        this.searchConsumer = new SearchConsumer();
        this.authConsumer = new AuthConsumer();
    }

    @QueryMapping
    public Users[] users(@ContextValue("Authorization") String AuthorizationHeader) {
        
        
        // System.out.println(authConsumer.Login("david3@example.com","User@123"));
        Boolean Auth = authConsumer.CheckRoute("/users","get",AuthorizationHeader);
        if(!Auth){
            Users[] x = new Users[1];
            return x;
        }

        Users[] allUser = userConsumer.users();
        for (Users user : allUser){
            if(user!=null && user.getPicture_id()!=null){
                String link = documentConsumer.getDocument(user.getPicture_id());
                user.setPicture_id(link);
            }
        }
        return allUser;
    }

    
    @QueryMapping
    public Users userById(@Argument String id, @ContextValue("Authorization") String AuthorizationHeader) {
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
        Users x= userConsumer.userById(id, AuthorizationHeader);
        if(x!=null && x.getPicture_id()!=null){
            String link = documentConsumer.getDocument(x.getPicture_id());
            x.setPicture_id(link);
        }
        return x;
    }

    

    @QueryMapping
    public Enrollment[] enrollements() {
        return userConsumer.enrollements();
    }

    @QueryMapping
    public Enrollment[] myCourses(@Argument String user_id) {
        return userConsumer.myCourses(user_id);
    }

    @QueryMapping
    public Enrollment isEnrolled(@Argument String user_id, @Argument String course_id) {
        return userConsumer.isEnrolled(user_id, course_id);
    }

    @QueryMapping
    public Students[] getCourses(@Argument String course_id) {

        Students[] students= userConsumer.getCourses(course_id);
        for(Students student: students){
            if(student.getUser()!=null && student.getUser().getPicture_id()!=null){
                String link = documentConsumer.getDocument(student.getUser().getPicture_id());
                student.getUser().setPicture_id(link);
            }
        }
        return students;
    }

    @MutationMapping
    public CreateResponse createUser(@Argument UsersInput user, @Argument String password, @Argument String confirmPassword, @Argument String rol) {
        // System.out.println(authConsumer.DecryptToken().toString());
        
        Users newUser = userConsumer.createUser(user);
        if(newUser!=null && newUser.getPicture_id()!=null){
            String link = documentConsumer.getDocument(newUser.getPicture_id());
            newUser.setPicture_id(link);
        }

        searchConsumer.AddUsersIndex(newUser.getId(), newUser.getName(), newUser.getLastname(), newUser.getNickname(), newUser.getPicture_id());
        RegisterResponse registerResponse = authConsumer.Register(user.getName(),user.getEmail(), password, confirmPassword,rol,newUser.getId());
        // return new CreateResponse(newUser,registerResponse.getToken());
        return new CreateResponse(newUser,"ESTEBAN METE TOKEN");
    }

    @MutationMapping
    public Users updateUser(@Argument UsersInput user, @Argument String id) {
        Users editedUser= userConsumer.updateUser(user, id);
        if(editedUser!=null && editedUser.getPicture_id()!=null){
            String link = documentConsumer.getDocument(editedUser.getPicture_id());
            editedUser.setPicture_id(link);
        }
        
        searchConsumer.UpdateUsersIndex(editedUser.getId(), editedUser.getName(), editedUser.getLastname(), editedUser.getNickname(), editedUser.getPicture_id());
        return editedUser;
    }

    @MutationMapping
    public Users deleteUser(@Argument String id) {
        Users deletedUser= userConsumer.deleteUser(id);
        if(deletedUser!=null && deletedUser.getPicture_id()!=null){
            documentConsumer.deleteDocument(deletedUser.getPicture_id());
        }
        searchConsumer.DeleteIndex(deletedUser.getId());
        return deletedUser;
    }

    @MutationMapping
    public Users updateMe(@Argument UsersInput user, @Argument String id) {
        Users editedUser= userConsumer.updateMe(user, id);
        if(editedUser!=null && editedUser.getPicture_id()!=null){
            String link = documentConsumer.getDocument(editedUser.getPicture_id());
            editedUser.setPicture_id(link);
        }
        searchConsumer.UpdateUsersIndex(editedUser.getId(), editedUser.getName(), editedUser.getLastname(), editedUser.getNickname(), editedUser.getPicture_id());
        return editedUser;
    }

    @MutationMapping
    public Users deleteMe(@Argument String id) {
        Users userDeleted = userConsumer.deleteMe(id);
        if(userDeleted!=null && userDeleted.getPicture_id()!=null){
            documentConsumer.deleteDocument(userDeleted.getPicture_id());
        }
        searchConsumer.DeleteIndex(userDeleted.getId());
        return userDeleted;
    }

    @MutationMapping
    public Enrollment createEnrollment(@Argument String course_id, @Argument String user_id) {
        return userConsumer.createEnrollment(course_id, user_id);
    }

    @MutationMapping
    public Enrollment updateEnrollment(@Argument EnrollInput enrollment, @Argument String course_id,
            @Argument String user_id) {
        return userConsumer.updateEnrollment(enrollment, course_id, user_id);
    }

    @MutationMapping
    public Enrollment deleteEnrollment(@Argument String course_id, @Argument String user_id) {
        return userConsumer.deleteEnrollment(course_id, user_id);
    }
}
