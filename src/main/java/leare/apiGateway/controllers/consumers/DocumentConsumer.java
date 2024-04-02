package leare.apiGateway.controllers.consumers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import leare.apiGateway.models.ObjectWhitPicture;
import leare.apiGateway.models.DocumentModels.DocumentGet;
import leare.apiGateway.models.DocumentModels.DocumentGetValue;
import leare.apiGateway.models.DocumentModels.DocumentPostSuccess;
import leare.apiGateway.models.DocumentModels.batch.CustomMapDeserializer;
// import leare.apiGateway.models.DocumentModels.batch.CustomMapDeserializer;
import leare.apiGateway.models.DocumentModels.batch.GetBatchResponse;
import leare.apiGateway.models.DocumentModels.batch.VideoInfo;
import leare.apiGateway.models.SearchModels.ResponsePost;
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

    public DocumentGet getDocument(String id) {
        try {
            return documentClient.get()
                    .uri("/read/{id}", id)
                    .retrieve()
                    .bodyToMono(DocumentGet.class)
                    .block(); // .block() se usa por simplicidad pero deberia ser asincrono

        } catch (Exception e) {
            return new DocumentGet(false, new DocumentGetValue(null, null, null, null, null, null));
        }

    }

    public DocumentPostSuccess deleteDocument(String userId,String id) {
        if (id!= null) {
            return documentClient.delete()
                .uri("/delete/{userId}/{id}",userId,id)
                .retrieve()
                .bodyToMono(DocumentPostSuccess.class)
                .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        }
        return new DocumentPostSuccess(true);
        

    }

    public DocumentPostSuccess batchDeleteDocument(String userId, String[] id) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("UserId", userId);
        requestBody.put("VideoIds", id);

        return documentClient.post()
                .uri("/delete/Batch/")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(DocumentPostSuccess.class)
                .block();
    }

    public GetBatchResponse batchGetDocuments(String[] id) {
        try{
        Map<String, String[]> requestBody = new HashMap<>();
        requestBody.put("ids", id);
        
        String jsonString = documentClient.post()
                .uri("/read/Batch")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(jsonString);
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<Map<String, VideoInfo>>(){}.getType(), new CustomMapDeserializer())
            .create();

        GetBatchResponse BatchResponse = gson.fromJson(jsonString, GetBatchResponse.class);
        Map<String, VideoInfo> valueMap = BatchResponse.getValue();
        // Gson gson = new Gson();
        // GetBatchResponse BatchResponse = gson.fromJson(jsonString,
        // GetBatchResponse.class);
        
        // // Type mapType = new TypeToken<Map<String, VideoInfo>>() {}.getType();

        // // Parsing JSON into your object

        // Gson gson = new GsonBuilder()
        // .create();
        System.out.println(BatchResponse.getValue().get("13").getFilePath());
        System.out.println(BatchResponse.getValue().get("13").getFilePath());
        System.out.println(BatchResponse.getValue().get("14").getFilePath());
        return BatchResponse;
    }
    catch (Exception e ){
        System.out.println("e");
        System.out.println(e);
        System.out.println(e);
        return new GetBatchResponse(false, null);
 
    }
        // return gson.fromJson(jsonString, GetBatchResponse.class);
    }

    // public <T extends ObjectWhitPicture> List<T> updatePictureLink(List<T> items)
    // {
    // for (T item : items) {
    // if (item != null && item.getPicture_id() != null) {
    // String link = documentConsumer.getDocument(item.getPicture_id());
    // item.setPicture_id(link);
    // }
    // }
    // return items;
    // }

    public <T extends ObjectWhitPicture> T[] updatePictureLink(T[] items) {
        for (T item : items) {
            this.updatePictureLink(item);
        }
        return items;
    }

    public <T extends ObjectWhitPicture> T updatePictureLink(T item) {
        if (item != null && item.getPicture_id() != null) {
            String link = this.getDocument(item.getPicture_id()).getValue().getFilePath();
            System.out.println(link);
            item.setPicture_id(link);
            return item;
        }
        item.setPicture_id("notFound");
        return item;
    }

    public <T extends ObjectWhitPicture> T deletePictureLinks(T item, String userId) {
        if (item != null && item.getPicture_id() != null) {
            DocumentPostSuccess link = this.deleteDocument(userId, item.getPicture_id());
            item.setPicture_id(null);
        }
        return item;
    }

    // public <T extends ObjectWhitPicture> T[] deletePictureLinks(T[] items) {
    // for (T item : items) {
    // this.deletePictureLinks(item);
    // }
    // return items;
    // }

}
