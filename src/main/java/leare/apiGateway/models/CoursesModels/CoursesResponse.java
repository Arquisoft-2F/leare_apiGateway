package leare.apiGateway.models.CoursesModels;

public class CoursesResponse  {

    private Category category;
    private CourseByCategory[] courses;


    public CoursesResponse(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public CourseByCategory[] getCourses() {
        return courses;
    }
    public void setCourses(CourseByCategory[] courses) {
        this.courses = courses;
    }
   
    

}