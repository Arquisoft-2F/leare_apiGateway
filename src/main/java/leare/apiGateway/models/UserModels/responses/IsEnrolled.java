package leare.apiGateway.models.UserModels.responses;

import leare.apiGateway.models.UserModels.Enrollment;

public class IsEnrolled {
    private Boolean isEnrolled;
    private String message;
    private Enrollment enrollment;


    public IsEnrolled(Boolean isEnrolled, String message, Enrollment enrollment) {
        this.isEnrolled = isEnrolled;
        this.message = message;
        this.enrollment = enrollment;
    }

    public Boolean isIsEnrolled() {
        return this.isEnrolled;
    }

    public Boolean getIsEnrolled() {
        return this.isEnrolled;
    }

    public void setIsEnrolled(Boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Enrollment getEnrollment() {
        return this.enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }


}
