package leare.apiGateway.models.UserModels;

public class Enrollment extends EnrollInput{

    private String course_id;
    private String user_id;

    public Enrollment(float progress, float score, String listed_on, String last_seen, String created_at,
            String updates_at, String course_id, String user_id) {
        super(progress, score, listed_on, last_seen, created_at, updates_at);
        this.course_id = course_id;
        this.user_id = user_id;
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
