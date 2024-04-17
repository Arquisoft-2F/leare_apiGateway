package leare.apiGateway.models.CoursesModels;


public class CoursesResponse  {

    private Category category;
    private CourseModify[] courses;


    public CoursesResponse(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public CourseModify[] getCourses() {
        return courses;
    }
    public void setCourses(CourseModify[] courses) {
        this.courses = courses;
    }
   
    

}