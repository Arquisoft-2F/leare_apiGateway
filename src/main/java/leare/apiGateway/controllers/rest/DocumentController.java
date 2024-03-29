package leare.apiGateway.controllers.rest;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.controllers.consumers.AuthConsumer;
import leare.apiGateway.controllers.consumers.SearchConsumer;
import leare.apiGateway.validation.UserValidation;
import reactor.core.publisher.Mono;
import leare.apiGateway.models.DocumentModels.DocumentPost;

@RestController
public class DocumentController {

    private final WebClient documentClient;
    private final AuthConsumer auth;

    public DocumentController() {
        String url = "http://document-server";
        String port = "3004";
        this.documentClient = WebClient.create(url + ":" + port);

        
        this.auth = new AuthConsumer();
    }

    @CrossOrigin
    // @GetMapping("/AddDocument")
    @PostMapping(path = "/AddDocument", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public DocumentPost add(@RequestPart MultipartFile content, @RequestPart String file_name,
            @RequestPart String data_type, @RequestPart String user_id) {
        UUID video_id = UUID.randomUUID();

        try {
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            formData.add("content", content);
            formData.add("file_name", file_name);
            formData.add("data_type", data_type);
            formData.add("user_id", user_id);
            formData.add("video_id", video_id.toString());
            
            WebClient.ResponseSpec x = documentClient.post()
            .uri("/posts")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(formData))
            .retrieve();
        //     // .bodyToMono(DocumentPost.class)
        //     // .block();
        //     // .bodyToMono(String.class)
        return new DocumentPost(true);
        
        }
        catch(Exception e){
            return new DocumentPost(false);  
        }
        //     .retrieve();
        //     // .bodyToMono(DocumentPost.class)
        //     // .block();
        //     // .bodyToMono(String.class)
        //     System.out.println("//////////////////1"+x);
        //     try{
        //         try{

        //             // DocumentPost z = x.bodyToMono(DocumentPost.class).block();
        //             Mono<DocumentPost> z = x.bodyToMono(DocumentPost.class);
                    
        //             return new DocumentPost(true,z.block() .toString());
        //             // return new DocumentPost(true,"es documento");
        //             // return z.block();
        //         }
        //         catch(Exception e){
        //         System.out.println(e);
        //         }
        //         try{
        //             // x.bodyToMono(String.class);
        //             Mono<String> z = x.bodyToMono(String.class);
        //             // return new DocumentPost(true,"es string");
        //             return new DocumentPost(true,z.block() ); 
        //         }
        //         catch(Exception e){

        //         }
        //     }
        //     catch(Exception e){
        //     return new DocumentPost(true,"A NADA LE ENTRA");

        //     }

        //     return new DocumentPost(true,"");
            
        //     // .uri("/posts")
        //     // .bodyValue(new HashMap<String, String>() {{
        //     // put("id", id);
        //     // put("description", description);
        //     // put("name", name);
        //     // put("picture", picture);
        //     // put("type", "Course");
        //     // }})
        //     // // .header("Authorization", token)
        //     // .retrieve()
        //     // .bodyToMono(String.class)
        //     // .block();
        // } catch (Exception e) {
        //     System.out.println("!!!!");
        //     System.out.println(e);
        //     return new DocumentPost(false,e.toString());
        // }
    }

    @CrossOrigin
    @GetMapping("/UpdateDocument")
    public DocumentPost del() {
        return this.add(null, null, null, null);
    }
}
