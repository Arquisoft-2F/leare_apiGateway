package leare.apiGateway.controllers.consumers;

import java.util.HashMap;

import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.SearchModels.ResponsePost;
import leare.apiGateway.models.SearchModels.ResponsePost.Post;

public class SearchConsumer {

    private final WebClient SearchClient;

    public SearchConsumer() {
        this.SearchClient = WebClient.create("http://search-web:3005");
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

            Post[] response =  SearchClient.get()
                        .uri("/posts?q="+query)
                        // .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(Post[].class)
                        .block(); // .block() se usa por simplicidad pero deberia ser asincrono
            for(Post x : response){
                System.out.println(x.getId());
            }
            return null;

        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }


    }


}
