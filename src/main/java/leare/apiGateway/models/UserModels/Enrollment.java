package leare.apiGateway.models.UserModels;

public class Enrollment extends EnrollInput{

    private String course_id;
    private String user_id;

    public Enrollment(String course_id, String user_id){
        super();
        this.course_id=course_id;
        this.user_id=user_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
