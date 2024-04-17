package leare.apiGateway.models.CoursesModels;
import leare.apiGateway.models.UserModels.Users;

public class CourseModify extends CourseByCategory{

    private Users creator;

    public CourseModify(CourseByCategory c, Users creator) {
        super(c.getCourse_id(), c.getCourse_name(), c.getCourse_description(), c.getCreator_id(), c.getChat_id(), c.isIs_public(), c.getPicture_id(), c.getCreated_at(), c.getUpdated_at(),
                c.getCategories());
        this.creator=creator;
        //TODO Auto-generated constructor stub
    }

    public Users getCreator() {
        return creator;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }

    

}