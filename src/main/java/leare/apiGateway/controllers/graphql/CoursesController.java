package leare.apiGateway.controllers.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.CoursesModels.Category;
import leare.apiGateway.models.CoursesModels.EditCategoryInput;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CoursesController {
    private final WebClient webClient;

    @Autowired
    public CoursesController(WebClient.Builder webClientBuilder) {
        String url = "http://courses-web";
        String port = "3003";
        this.webClient = webClientBuilder.baseUrl(url + ":" + port).build();
    }

    @QueryMapping
    public Category[] categories() {
        return webClient
            .get()
            .uri("/categories")
            .retrieve()
            .bodyToMono(Category[].class)
            .block();
    }

    @QueryMapping
    public Category categoryById(@Argument String id) {
        return webClient
            .get()
            .uri("/categories/{id}", id)
            .retrieve()
            .bodyToMono(Category.class)
            .block();
    }

    @MutationMapping
    public Category createCategory(@Argument String category_name) {
        Map<String, Object> body = new HashMap<>();
        body.put("category_name", category_name);

        return webClient
            .post()
            .uri("/categories")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(Category.class)
            .block();
    }

    @MutationMapping
    public Category editCategory(@Argument EditCategoryInput input) {
        Map<String, Object> body = new HashMap<>();
        body.put("category_name", input.getCategory_name());

        return webClient
            .patch()
            .uri("/categories/{id}", input.getCategory_id())
            .bodyValue(body)
            .retrieve()
            .bodyToMono(Category.class)
            .block();
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument String id) {
        webClient
            .delete()
            .uri("/categories/{id}", id)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
        return true;
    }
}
