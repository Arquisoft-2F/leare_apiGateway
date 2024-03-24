package leare.apiGateway.models.UserModels;

public class Students {

    private Users user;
    private Enrollment course;

    public Students() {
    }
    public Students(Users user, Enrollment course) {
        this.user = user;
        this.course = course;
    }
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public Enrollment getCourse() {
        return course;
    }
    public void setCourse(Enrollment course) {
        this.course = course;
    }

    

}
