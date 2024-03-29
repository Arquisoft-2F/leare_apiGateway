package leare.apiGateway.models.CoursesModels;

import java.util.List;

public class EditCourseInput {
    private String course_id;
    private String course_name;
    private String course_description;
    private String creator_id;
    private Boolean is_public;
    private String picture_id;
    private List<String> categories;

    public EditCourseInput(String course_id, String course_name, String course_description, String creator_id,
            Boolean is_public, String picture_id, List<String> categories) {

        this.course_id = course_id;
        this.course_name = course_name;
        this.course_description = course_description;
        this.creator_id = creator_id;
        this.is_public = is_public;
        this.picture_id = picture_id;
        this.categories = categories;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    

    


}
