package leare.apiGateway.controllers.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import leare.apiGateway.models.CoursesModels.Category;
import leare.apiGateway.models.CoursesModels.Course;
import leare.apiGateway.models.CoursesModels.CourseByCategory;
import leare.apiGateway.models.CoursesModels.CourseModule;
import leare.apiGateway.models.CoursesModels.CreateCourseInput;
import leare.apiGateway.models.CoursesModels.CreateModuleInput;
import leare.apiGateway.models.CoursesModels.EditCategoryInput;
import leare.apiGateway.models.CoursesModels.EditCourseInput;
import leare.apiGateway.models.CoursesModels.EditModuleInput;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CoursesResolver {
    private final WebClient webClient;

    @Autowired
    public CoursesResolver(WebClient.Builder webClientBuilder) {
        String url = "http://courses-web";
        String port = "3003";
        this.webClient = webClientBuilder.baseUrl(url + ":" + port).build();
    }

    // CATEGORIES

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

    // COURSES

    @QueryMapping
    public CourseByCategory[] coursesByCategory(@Argument("categories") List<String> categories) {
        Map<String, Object> body = Map.of("categories", categories);

        return ((RequestBodySpec) webClient
            .get()
            .uri("/courses/categories"))
            .bodyValue(body)
            .retrieve()
            .bodyToMono(CourseByCategory[].class)
            .block();
    }

    @QueryMapping
    public Course[] listCourses(@Argument int page) {
        return webClient
            .get()
            .uri("/listcourses/{page}", page)
            .retrieve()
            .bodyToMono(Course[].class)
            .block();
    }

    @QueryMapping
    public Course courseById(@Argument String id) {
        return webClient
            .get()
            .uri("/courses/{id}", id)
            .retrieve()
            .bodyToMono(Course.class)
            .block();  
    }

    @MutationMapping
    public Course createCourse(@Argument CreateCourseInput input) {
        return webClient
            .post()
            .uri("/courses")
            .bodyValue(input)
            .retrieve()
            .bodyToMono(Course.class)
            .block();  
    }

    @MutationMapping
    public Course editCourse(@Argument EditCourseInput input) {
        Map<String, Object> body = new HashMap<>(); // This is neccesary because "public" is a reserved word in Java
        body.put("course_name", input.getCourse_name());
        body.put("course_description", input.getCourse_description());
        body.put("creator_id", input.getCreator_id());
        body.put("public", input.getIs_public());
        body.put("picture_id", input.getPicture_id());
        body.put("categories", input.getCategories());

        return webClient
            .patch()
            .uri("/courses/{id}", input.getCourse_id())
            .bodyValue(body)
            .retrieve()
            .bodyToMono(Course.class)
            .block();  
    }

    @MutationMapping
    public Boolean deleteCourse(@Argument String id) {
        webClient
            .delete()
            .uri("/courses/{id}", id)
            .retrieve()
            .bodyToMono(Void.class)
            .block(); 
            
        return true;
    }

    // MODULES

    @QueryMapping
    public CourseModule[] courseModules(@Argument String course_id, @Argument int page) {
        return webClient
            .get()
            .uri("/coursemodules/{course_id}/{page}", course_id, page)
            .retrieve()
            .bodyToMono(CourseModule[].class)
            .block();
    }

    @QueryMapping
    public CourseModule moduleById(@Argument String id) {
        return webClient
            .get()
            .uri("/modules/{id}", id)
            .retrieve()
            .bodyToMono(CourseModule.class)
            .block();
    }

    @MutationMapping
    public CourseModule createModule(@Argument CreateModuleInput input) { 
        return webClient
            .post()
            .uri("/modules")
            .bodyValue(input)
            .retrieve()
            .bodyToMono(CourseModule.class)
            .block();
    }

    @MutationMapping
    public CourseModule editModule(@Argument EditModuleInput moduleEdit) {
        return webClient
            .patch()
            .uri("/modules/{id}", moduleEdit.getModule_id())
            .bodyValue(moduleEdit)
            .retrieve()
            .bodyToMono(CourseModule.class)
            .block();
    }

    @MutationMapping
    public Boolean deleteModule(@Argument String id) {
        webClient
            .delete()
            .uri("/modules/{id}", id)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
        return true;
    }

}
