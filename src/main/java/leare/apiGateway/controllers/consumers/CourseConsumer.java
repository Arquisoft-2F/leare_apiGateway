package leare.apiGateway.controllers.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import leare.apiGateway.models.CoursesModels.Category;
import leare.apiGateway.models.CoursesModels.Course;
import leare.apiGateway.models.CoursesModels.CourseByCategory;
import leare.apiGateway.models.CoursesModels.CourseModule;
import leare.apiGateway.models.CoursesModels.CreateCourseInput;
import leare.apiGateway.models.CoursesModels.CreateModuleInput;
import leare.apiGateway.models.CoursesModels.CreateSectionInput;
import leare.apiGateway.models.CoursesModels.EditCategoryInput;
import leare.apiGateway.models.CoursesModels.EditCourseInput;
import leare.apiGateway.models.CoursesModels.EditModuleInput;
import leare.apiGateway.models.CoursesModels.EditSectionInput;
import leare.apiGateway.models.CoursesModels.ModuleSection;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourseConsumer {
    private final WebClient webClient;

    @Autowired
    public CourseConsumer(WebClient.Builder webClientBuilder) {
        String baseUrl = "http://courses-web";
        String port = "3003";
        this.webClient = webClientBuilder.baseUrl(baseUrl + ":" + port).build();
    }

    public Category[] getCategories() {
        return webClient
                .get()
                .uri("/categories")
                .retrieve()
                .bodyToMono(Category[].class)
                .block();
    }

    public Category getCategoryById(String id) {
        return webClient
                .get()
                .uri("/categories/{id}", id)
                .retrieve()
                .bodyToMono(Category.class)
                .block();
    }

    public Category createCategory(String categoryName) {
        Map<String, Object> body = new HashMap<>();
        body.put("category_name", categoryName);

        return webClient
                .post()
                .uri("/categories")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Category.class)
                .block();
    }

    public Category editCategory(EditCategoryInput input) {
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

    public Boolean deleteCategory(String id) {
        String message =webClient
                .delete()
                .uri("/categories/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> (String) map.get("message"))
                .block();
        if(message.equals("deleted successfully"))
        {
            return true;
        }
        return false;
    }

    public CourseByCategory[] getCoursesByCategory(List<String> categories) {
        Map<String, Object> body = Map.of("categories", categories);

        return ((RequestBodySpec) webClient
            .get()
            .uri("/courses/categories"))
            .bodyValue(body)
            .retrieve()
            .bodyToMono(CourseByCategory[].class)
            .block();
    }

    public Course[] listCourses(int page) {
        return webClient
                .get()
                .uri("/listcourses/{page}", page)
                .retrieve()
                .bodyToMono(Course[].class)
                .block();
    }

    public Course getCourseById(String id) {
        return webClient
                .get()
                .uri("/courses/{id}", id)
                .retrieve()
                .bodyToMono(Course.class)
                .block();
    }

    public Course createCourse(CreateCourseInput input) {
        return webClient
                .post()
                .uri("/courses")
                .bodyValue(input)
                .retrieve()
                .bodyToMono(Course.class)
                .block();
    }

    public Course editCourse(EditCourseInput input) {
        Map<String, Object> body = new HashMap<>();
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

    public Boolean deleteCourse(String id) {
        String message = webClient
                .delete()
                .uri("/courses/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> (String) map.get("message"))
                .block();
        if(message.equals("deleted successfully"))
        {
            return true;
        }
        return false;
    }

    public CourseModule[] getCourseModules(String courseId, int page) {
        return webClient
                .get()
                .uri("/coursemodules/{course_id}/{page}", courseId, page)
                .retrieve()
                .bodyToMono(CourseModule[].class)
                .block();
    }

    public CourseModule getModuleById(String id) {
        return webClient
                .get()
                .uri("/modules/{id}", id)
                .retrieve()
                .bodyToMono(CourseModule.class)
                .block();
    }

    public CourseModule createModule(CreateModuleInput input) {
        return webClient
                .post()
                .uri("/modules")
                .bodyValue(input)
                .retrieve()
                .bodyToMono(CourseModule.class)
                .block();
    }

    public CourseModule editModule(EditModuleInput input) {
        return webClient
                .patch()
                .uri("/modules/{id}", input.getModule_id())
                .bodyValue(input)
                .retrieve()
                .bodyToMono(CourseModule.class)
                .block();
    }

    public Boolean deleteModule(String id) {
        String message =webClient
                .delete()
                .uri("/modules/{id}", id)
                .retrieve()
                .bodyToMono(Map.class) // Assuming the response is JSON and you're using a Map for deserialization
                .map(map -> (String) map.get("message"))
                .block();
        if(message.equals("deleted successfully"))
        {
            return true;
        }
        return false;
    }

    public ModuleSection[] getModuleSections(String moduleId, int page) {
        return webClient
                .get()
                .uri("/modules/{module_id}/sections/{page}", moduleId, page)
                .retrieve()
                .bodyToMono(ModuleSection[].class)
                .block();
    }

    public ModuleSection getSectionById(String id) {
        return webClient
                .get()
                .uri("/sections/{id}", id)
                .retrieve()
                .bodyToMono(ModuleSection.class)
                .block();
    }

    public ModuleSection createSection(CreateSectionInput input) {
        return webClient
                .post()
                .uri("/sections")
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ModuleSection.class)
                .block();
    }

    public ModuleSection editSection(EditSectionInput input) {
        return webClient
                .patch()
                .uri("/sections/{id}", input.getSection_id())
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ModuleSection.class)
                .block();
    }

    public Boolean deleteSection(String id) {
        String message = webClient
                .delete()
                .uri("/sections/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> (String) map.get("message"))
                .block();
        if(message.equals("deleted successfully"))
        {
            return true;
        }
        return false;
    }

    public String moduleCreator(String id) {
        return webClient
                .get()
                .uri("/modules/{id}/creator", id)
                .retrieve()
                .bodyToMono(Map.class) // Assuming the response is JSON and you're using a Map for deserialization
                .map(map -> (String) map.get("creator_id"))
                .block();
    }

    public String sectionCreator(String id) {
        return webClient
                .get()
                .uri("/sections/{id}/creator", id)
                .retrieve()
                .bodyToMono(Map.class) // Assuming the response is JSON and you're using a Map for deserialization
                .map(map -> (String) map.get("creator_id"))
                .block();
    }
}
