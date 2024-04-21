package leare.apiGateway.models.UserModels.responses;

import leare.apiGateway.models.CoursesModels.Course;
import leare.apiGateway.models.UserModels.EnrollInput;
import leare.apiGateway.models.UserModels.Enrollment;

public class EnrollmentResponse extends EnrollInput{

    private Course course;
    private String user_id;

    public EnrollmentResponse(Enrollment enroll,Course course) {
        super(enroll.getProgress(), enroll.getScore(), enroll.getListed_on(), enroll.getLast_seen(), enroll.getCreated_at(), enroll.getUpdates_at());
        this.course = course;
        this.user_id = user_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
