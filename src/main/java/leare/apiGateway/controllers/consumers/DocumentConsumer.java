package leare.apiGateway.controllers.consumers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import leare.apiGateway.controllers.graphql.ObjectWhitPicture;
import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;
import leare.apiGateway.models.UserModels.Students;
import leare.apiGateway.models.UserModels.Users;
import leare.apiGateway.models.UserModels.UsersInput;
import leare.apiGateway.models.UserModels.responses.UserResponse;
import leare.apiGateway.validation.UserValidation;

@Component
public class DocumentConsumer {
    
    private final WebClient documentClient;

    @Autowired
    public DocumentConsumer(WebClient.Builder webClientBuilder) {
        String url = "http://document-server";
        String port = "3004";
        this.documentClient = webClientBuilder.baseUrl(url + ":" + port).build();
    }

    private String extractURL(String text) {
        String[] matches = Pattern.compile("https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)")
                          .matcher(text)
                          .results()
                          .map(MatchResult::group)
                          .toArray(String[]::new);
        System.out.println(Arrays.toString(matches));
        return String.join("",matches);
    }

    public String getDocument( String id) {
        try {
        String fullDOcument = documentClient.get()
                .uri("/read/{id}",id)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

            return this.extractURL(fullDOcument);
        } catch (Exception e) {
            return "notFound";
        }



    }

    public String deleteDocument(String id) {
        return documentClient.delete()
                .uri("/read/{id}",id)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono

    }

        // public <T extends ObjectWhitPicture> List<T> updatePictureLinks(List<T> items) {
    //     for (T item : items) {
    //         if (item != null && item.getPicture_id() != null) {
    //             String link = documentConsumer.getDocument(item.getPicture_id());
    //             item.setPicture_id(link);
    //         }
    //     }
    //     return items;
    // }


    
    public <T extends ObjectWhitPicture> T[] updatePictureLinks(T[] items) {
        for (T item : items) {
            this.updatePictureLinks(item);
        }
        return items;
    }
    public <T extends ObjectWhitPicture> T updatePictureLinks(T item) {
        if (item != null && item.getPicture_id() != null) {
            String link = this.getDocument(item.getPicture_id());
            item.setPicture_id(link);
        }
        return item;
    }
    
    public <T extends ObjectWhitPicture> T deletePictureLinks(T item) {
        if (item != null && item.getPicture_id() != null) {
            String link = this.deleteDocument(item.getPicture_id());
            item.setPicture_id(link);
        }
        return item;
    }
    
    public <T extends ObjectWhitPicture> T[] deletePictureLinks(T[] items) {
        for (T item : items) {
            this.deletePictureLinks(item);
        }
        return items;
    }
    
}
