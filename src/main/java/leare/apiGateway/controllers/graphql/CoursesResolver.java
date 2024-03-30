package leare.apiGateway.controllers.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import leare.apiGateway.controllers.consumers.CourseConsumer;
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

import java.util.List;

@Controller
public class CoursesResolver {
    private final CourseConsumer coursesConsumer;

    @Autowired
    public CoursesResolver() {
        this.coursesConsumer = new CourseConsumer();
    }

    // CATEGORY

    @QueryMapping
    public Category[] categories() {
        return coursesConsumer.getCategories();
    }

    @QueryMapping
    public Category categoryById(@Argument String id) {
        return coursesConsumer.getCategoryById(id);
    }

    @MutationMapping
    public Category createCategory(@Argument String category_name) {
        return coursesConsumer.createCategory(category_name);
    }

    @MutationMapping
    public Category editCategory(@Argument EditCategoryInput input) {
        return coursesConsumer.editCategory(input);
    }
    
    @MutationMapping
    public Boolean deleteCategory(@Argument String id) {
        coursesConsumer.deleteCategory(id);
        return true;
    }

    // COURSE

    @QueryMapping
    public CourseByCategory[] coursesByCategory(@Argument("categories") List<String> categories) {
        return coursesConsumer.getCoursesByCategory(categories);
    }

    @QueryMapping
    public Course[] listCourses(@Argument int page) {
        return coursesConsumer.listCourses(page);
    }

    @QueryMapping
    public Course courseById(@Argument String id) {
        return coursesConsumer.getCourseById(id);
    }

    @MutationMapping
    public Course createCourse(@Argument CreateCourseInput input) {
        return coursesConsumer.createCourse(input);
    }

    @MutationMapping
    public Course editCourse(@Argument EditCourseInput input) {
        return coursesConsumer.editCourse(input);
    }

    @MutationMapping
    public Boolean deleteCourse(@Argument String id) {
        coursesConsumer.deleteCourse(id);
        return true;
    }

    // MODULE

    @QueryMapping
    public CourseModule[] courseModules(@Argument String course_id, @Argument int page) {
        return coursesConsumer.getCourseModules(course_id, page);
    }

    @QueryMapping
    public CourseModule moduleById(@Argument String id) {
        return coursesConsumer.getModuleById(id);
    }

    @MutationMapping
    public CourseModule createModule(@Argument CreateModuleInput input) {
        return coursesConsumer.createModule(input);
    }

    @MutationMapping
    public CourseModule editModule(@Argument EditModuleInput input) {
        return coursesConsumer.editModule(input);
    }

    @MutationMapping
    public Boolean deleteModule(@Argument String id) {
        coursesConsumer.deleteModule(id);
        return true;
    }

    // SECTION

    @QueryMapping
    public ModuleSection[] moduleSections(@Argument String module_id, @Argument int page) {
        return coursesConsumer.getModuleSections(module_id, page);
    }

    @QueryMapping
    public ModuleSection sectionById(@Argument String id) {
        return coursesConsumer.getSectionById(id);
    }

    @MutationMapping
    public ModuleSection createSection(@Argument CreateSectionInput input) {
        return coursesConsumer.createSection(input);
    }

    @MutationMapping
    public ModuleSection editSection(@Argument EditSectionInput input) {
        return coursesConsumer.editSection(input);
    }

    @MutationMapping
    public Boolean deleteSection(@Argument String id) {
        coursesConsumer.deleteSection(id);
        return true;
    }
}