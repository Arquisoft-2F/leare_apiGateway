package leare.apiGateway.controllers.graphql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import leare.apiGateway.models.AuthModels.DecryptedToken;
import leare.apiGateway.models.AuthModels.RegisterResponse;
import leare.apiGateway.models.DocumentModels.batch.GetBatchResponse;
import leare.apiGateway.models.DocumentModels.batch.VideoInfo;
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
import leare.apiGateway.errors.AuthError;
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
    public UserResolver(UserConsumer userConsumer, DocumentConsumer documentConsumer, SearchConsumer searchConsumer,
            AuthConsumer authConsumer) {
        this.userConsumer = userConsumer;
        this.documentConsumer = documentConsumer;
        this.searchConsumer = searchConsumer;
        this.authConsumer = authConsumer;
    }

    @QueryMapping
    public Users[] users(@ContextValue("Authorization") String AuthorizationHeader) throws Exception {

        // System.out.println(authConsumer.Login("david3@example.com","User@123"));
        Boolean Auth = authConsumer.CheckRoute("/users", "get", AuthorizationHeader);

        if (!Auth) {
            // Users[] x = new Users[1];
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        Users[] allUser = userConsumer.users();
        String[] pictureIds = new String[allUser.length];

        for (int i = 0; i < allUser.length; i++) {
            pictureIds[i] = allUser[i].getPicture_id();
        }
        System.out.println(Arrays.toString(pictureIds));
        GetBatchResponse allPictures = documentConsumer.batchGetDocuments(pictureIds);
        // Iterate over the values in the map and print each one
        for (VideoInfo videoInfo : allPictures.getValueMap().values()) {
            System.out.println("VideoInfo: " + videoInfo.getDate());
            System.out.println("VideoInfo: " + videoInfo.getFileName());
            System.out.println("VideoInfo: " + videoInfo.getFilePath());
            System.out.println("VideoInfo: " + videoInfo.getFileType());
            System.out.println("VideoInfo: " + videoInfo.getUserId());
            System.out.println("VideoInfo: " + videoInfo.getVideoId());
}

        // for (Map.Entry<String, VideoInfo> entry : allPictures.getValue().entrySet()) {
        //     String key = entry.getKey();
        //     VideoInfo value = entry.getValue();
        //     System.out.println("Key: " + key);
        //     System.out.println("Value: " + value);
        // }
        for (Users user : allUser) {
            try {
                String link = allPictures.getValueMap().get(user.getPicture_id()).getFilePath();
                // System.out.println(allPictures.getValue().get("14").getUserId());
                // System.out.println(allPictures.getValue().get("14").getFileName());
                // System.out.println(allPictures.getValue().get("14").getVideoId());
                
                // System.out.println("User picture_id: " + user.getPicture_id());
                // System.out.println("Link: " + link);
                user.setPicture_id(link);
            } catch (Exception e) {
                user.setPicture_id(null);
            }
        }
        // for (Users user : allUser) {
        //     if (user != null && user.getPicture_id() != null) {
        //         String link = documentConsumer.getDocument(user.getPicture_id()).getValue().getFilePath();
        //         user.setPicture_id(link);
        //     }
        // }
        // throw new Exception("NO ESTA AUTENTICADO ");

        return allUser;
    }

    @QueryMapping
    public Users userById(@Argument String id, @ContextValue("Authorization") String AuthorizationHeader)
            throws Exception {
        // !search

        // UUID x = UUID.randomUUID();
        // if( search.AddCourseIndex(x.toString(), "2", "3", "4") == false){
        // }
        // search.UpdateCourseIndex(x.toString(), "update", "update", "update");
        // x = UUID.randomUUID();
        // if( search.AddUsersIndex(x.toString(), "2", "3", "4","5") == false){
        // //do something with the error?
        // }
        // search.UpdateUsersIndex(x.toString(), "update", "update", "update","update");
        // x = UUID.randomUUID();
        // if( search.AddCategoryIndex(x.toString(), "2") == false){
        // //do something with the error?
        // }
        // search.UpdateCategoryIndex(x.toString(), "AQUI ESTOY");
        // search.DeleteIndex(x.toString());

        // !auth
        // //String route, String method, String token

        Boolean Auth = authConsumer.CheckRoute("/users/:id", "get", AuthorizationHeader);
        System.out.println(Auth);
        if (!Auth) {
            throw new Exception("Auth problem");
        }
        // !document
        Users x = userConsumer.userById(id);
        if (x != null && x.getPicture_id() != null) {
            String link = documentConsumer.getDocument(x.getPicture_id()).getValue().getFilePath();
            x.setPicture_id(link);
        }
        return x;
    }

    @QueryMapping
    public Enrollment[] enrollements(@ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/courses_users", "get", AuthorizationHeader);

        if (!Auth) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        return userConsumer.enrollements();
    }

    @QueryMapping
    public Enrollment[] myCourses(@Argument String user_id, @ContextValue("Authorization") String AuthorizationHeader)
            throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/users/:id/enroll", "get", AuthorizationHeader);
        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);
        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        if(token.getRole()=="admin"){
            return userConsumer.myCourses(user_id);
        }else{
            return userConsumer.myCourses(token.getUserID());
        }
    }

    @QueryMapping
    public Enrollment isEnrolled(@Argument String user_id, @Argument String course_id,
            @ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/users/:user_id/enroll/:course:id", "get", AuthorizationHeader);

        if (!Auth) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        return userConsumer.isEnrolled(user_id, course_id);
    }

    @QueryMapping
    public Students[] getCourses(@Argument String course_id, @ContextValue("Authorization") String AuthorizationHeader)
            throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/courses_users/:course_id/users", "get", AuthorizationHeader);

        if (!Auth) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }

        Students[] students = userConsumer.getCourses(course_id);
        for (Students student : students) {
            if (student.getUser() != null && student.getUser().getPicture_id() != null) {
                String link = documentConsumer.getDocument(student.getUser().getPicture_id()).getValue().getFilePath();
                student.getUser().setPicture_id(link);
            }
        }

        // TODO : USE DOCUMENT CONSUMER TO UPDATE PICTURE LINKS

        return students;
    }

    @MutationMapping
    public CreateResponse createUser(@Argument UsersInput user, @Argument String password,
            @Argument String confirmPassword, @Argument String rol) throws Exception {
        
        // Boolean Auth = authConsumer.CheckRoute("/users", "post", AuthorizationHeader);

        // if (!Auth) {
        //     throw new AuthError("Auth Problem : Acces denied to this route");
        // }

        Users newUser = userConsumer.createUser(user);
        // if(newUser!=null && newUser.getPicture_id()!=null){
        // String link = documentConsumer.getDocument(newUser.getPicture_id());
        // newUser.setPicture_id(link);
        // }

        newUser = documentConsumer.updatePictureLink(newUser);

        searchConsumer.AddUsersIndex(newUser.getId(), newUser.getName(), newUser.getLastname(), newUser.getNickname(),
                newUser.getPicture_id());
        RegisterResponse registerResponse = authConsumer.Register(user.getName(),user.getNickname(), user.getEmail(), password,
                confirmPassword, rol, newUser.getId());
        if(registerResponse.getFlag().equals("false")){
            userConsumer.deleteUser(newUser.getId());
             throw new AuthError("Auth Problem : Acces denied to this route");
        }
        return new CreateResponse(newUser,registerResponse.getToken());
        // return new CreateResponse(newUser, "ESTEBAN METE TOKEN");
    }

    @MutationMapping
    public Users updateUser(@Argument UsersInput user, @Argument String id,
            @ContextValue("Authorization") String AuthorizationHeader) throws Exception {

        Boolean Auth = authConsumer.CheckRoute("/users/:id", "patch", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);

        if (!Auth || token==null || (!token.getRole().equals("admin") && !token.getUserID().equals(id))) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        Users pasUser = userConsumer.userById(id);
        Users editedUser = userConsumer.updateUser(user, id);
        editedUser = documentConsumer.updatePictureLink(editedUser);
        RegisterResponse updatedAuth=authConsumer.updateAuth(editedUser.getName(),editedUser.getNickname(),editedUser.getEmail(),null,null,null,id);
        if(updatedAuth.getFlag().equals("false")){
            Users l = userConsumer.updateUser(pasUser, id);  
            throw new AuthError("Auth Problem : Acces denied to this route");  
        }
        searchConsumer.UpdateUsersIndex(editedUser.getId(), editedUser.getName(), editedUser.getLastname(),
                editedUser.getNickname(), editedUser.getPicture_id());
        return editedUser;
    }

    @MutationMapping
    public Users deleteUser(@Argument String id, @ContextValue("Authorization") String AuthorizationHeader)
            throws Exception {

        Boolean Auth = authConsumer.CheckRoute("/users/:id", "delete", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);
        if (!Auth || token==null || !token.getRole().equals("admin")) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        Users deletedUser = userConsumer.deleteUser(id);
        // if(deletedUser!=null && deletedUser.getPicture_id()!=null){
        // documentConsumer.deleteDocument(deletedUser.getPicture_id());
        // }
        RegisterResponse deletedAuth=authConsumer.deleteAuth(id);
        if(deletedAuth.getFlag().equals("false")){
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        documentConsumer.deleteDocument(token.getUserID(),deletedUser.getPicture_id());
        searchConsumer.DeleteIndex(deletedUser.getId());
        return deletedUser;
    }

    @MutationMapping
    public Users updateMe(@Argument UsersInput user,
            @ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        
        
        Boolean Auth = authConsumer.CheckRoute("/users/me", "patch", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);
        System.out.println(Auth);
        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        Users pasUser = userConsumer.userById(token.getUserID());
        Users editedUser = userConsumer.updateMe(user, token.getUserID());
        RegisterResponse updatedAuth=authConsumer.updateAuth(editedUser.getName(),editedUser.getNickname(),editedUser.getEmail(),null,null,null,token.getUserID());
        if(updatedAuth.getFlag().equals("false")){
            Users l = userConsumer.updateUser(pasUser, token.getUserID());  
            throw new AuthError("Auth Problem : Acces denied to this route");  
        }
        editedUser = documentConsumer.updatePictureLink(editedUser);
        searchConsumer.UpdateUsersIndex(editedUser.getId(), editedUser.getName(), editedUser.getLastname(),
                editedUser.getNickname(), editedUser.getPicture_id());
        return editedUser;
    }

    @MutationMapping
    public Users deleteMe(@ContextValue("Authorization") String AuthorizationHeader)
            throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/users/me", "delete", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);

        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        Users userDeleted = userConsumer.deleteMe(token.getUserID());
        RegisterResponse deletedAuth=authConsumer.deleteAuth(token.getUserID());
        if(deletedAuth.getFlag().equals("false")){
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        System.out.println(userDeleted.getPicture_id());
        documentConsumer.deleteDocument(token.getUserID(), userDeleted.getPicture_id());
        System.out.println("Sapo");
        Boolean x =searchConsumer.DeleteIndex(userDeleted.getId());
        System.out.println(x);
        return userDeleted;
    }

    @MutationMapping
    public Enrollment createEnrollment(@Argument String course_id, @Argument String user_id,
            @ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/courses_users", "post", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);

        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        if(token.getRole().equals("admin")){
            return userConsumer.createEnrollment(course_id, user_id);
        }else{
            return userConsumer.createEnrollment(course_id, token.getUserID());
        }
    }

    @MutationMapping
    public Enrollment updateEnrollment(@Argument EnrollInput enrollment, @Argument String course_id,
            @Argument String user_id, @ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/courses_users/:course_id/:user_id", "patch", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);

        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        if(token.getRole().equals("admin")||token.getRole().equals("teacher")){
            return userConsumer.updateEnrollment(enrollment, course_id, user_id);
        }
        return userConsumer.updateEnrollment(enrollment, course_id, token.getUserID());
    }

    @MutationMapping
    public Enrollment deleteEnrollment(@Argument String course_id, @Argument String user_id,
            @ContextValue("Authorization") String AuthorizationHeader) throws Exception {
        Boolean Auth = authConsumer.CheckRoute("/courses_users/:course_id/:user_id", "delete", AuthorizationHeader);

        DecryptedToken token = authConsumer.DecryptToken(AuthorizationHeader);

        if (!Auth || token==null) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        if(token.getRole().equals("admin")||token.getRole().equals("teacher")){
            return userConsumer.deleteEnrollment(course_id, user_id);
        }
        return userConsumer.deleteEnrollment(course_id, token.getUserID());
    }
}
