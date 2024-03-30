package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.http.MediaType;
import leare.apiGateway.models.SearchModels.Highlight;
import leare.apiGateway.models.SearchModels.Post;
import leare.apiGateway.models.SearchModels.ResponsePost;
import leare.apiGateway.validation.SearchValidation;

public class SearchConsumer {

    private final WebClient SearchClient;
    private final SearchValidation searchValidation;

    public SearchConsumer() {
        this.SearchClient = WebClient.create("http://search-web:3005");
        this.searchValidation = new SearchValidation();
    }

    public void Login() {

    }

    public void Register() {

    }

    public Boolean AddCourseIndex(String id, String description, String name,String picture){
        try{

            String response =  SearchClient.post()
                        .uri("/posts")
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("description", description);
                            put("name", name);
                            put("picture", picture);
                            put("type", "Course");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }


    }


    public Boolean AddUsersIndex(String id, String name, String lastname, String nickname,String picture){
        try{

            String response =  SearchClient.post()
                        .uri("/posts")
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("name", name);
                            put("lastname", lastname);
                            put("nickname", nickname);
                            put("picture", picture);
                            put("type", "User");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean AddCategoryIndex(String id, String name){
        try{

            String response =  SearchClient.post()
                        .uri("/posts")
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("name", name);
                            put("type", "Category");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }


    }
    //PUT
    public Boolean UpdateCourseIndex(String id, String description, String name,String picture){
        try{

            String response =  SearchClient.put()
                        .uri("/posts/"+id)
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("description", description);
                            put("name", name);
                            put("picture", picture);
                            put("type", "Course");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }


    }


    public Boolean UpdateUsersIndex(String id, String name, String lastname, String nickname,String picture){
        try{

            String response =  SearchClient.put()
                        .uri("/posts/"+id)
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("name", name);
                            put("lastname", lastname);
                            put("nickname", nickname);
                            put("picture", picture);
                            put("type", "User");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean UpdateCategoryIndex(String id, String name){
        try{

            String response =  SearchClient.put()
                        .uri("/posts/"+id)
                        .bodyValue(new HashMap<String, String>() {{
                            put("id", id);
                            put("name", name);
                            put("type", "Category");
                        }})
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }


    }
    //delete
    public Boolean DeleteIndex(String id){
        try{

            String response =  SearchClient.delete()
                        .uri("/posts/"+id)
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            return true;

        }
        catch(Exception e){
            return false;
        }


    }

    public ResponsePost[] getbyIndex(String query){
         try{

            ObjectMapper objectMapper = new ObjectMapper();
            ResponsePost[] response = SearchClient
                .get()
                .uri("/posts?q=" + query)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {
                        // Deserialize JSON response using Jackson
                        return objectMapper.readValue(jsonString, ResponsePost[].class);
                    } catch (Exception e) {
                        // Handle exception
                        e.printStackTrace();
                        return null;
                    }
                })
                .block();
            return response;
        } catch (WebClientResponseException ex) {
            return new ResponsePost[]{searchValidation.SearchClientEx(ex)};
        }
            
        //     Highlight[] response =  SearchClient.get()
        //                 .uri("/posts?q="+query)
        //                 // .header("Authorization", token)
        //                 .retrieve()
        //                 .bodyToMono(Highlight[].class)
        //                 .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        //     Post[] responsePost =  SearchClient.get()
        //                 .uri("/posts?q="+query)
        //                 // .header("Authorization", token)
        //                 .retrieve()
        //                 .bodyToMono(Post[].class)
        //                 .block(); // .block() se usa por simplicidad pero deberia ser asincrono
        //     for(Highlight x : response){
        //         System.out.println(x.getName());
        //         System.out.println(x.getLastname());
        //         System.out.println(x.getNickname());
        //         System.out.println(x.getDescription());
        //         System.out.println("Highlight");
        //     }
        //     for(Post x : responsePost){
        //         System.out.println(x.getId());
        //         System.out.println("Post");
        //     }
        //     return null;

        // // }
        // // catch(Exception e){
        // //     System.out.println(e);
        // //     System.out.println("e");
        // //     return null;
        // // }


    }


}
