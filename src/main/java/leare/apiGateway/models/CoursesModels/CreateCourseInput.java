package leare.apiGateway.models.CoursesModels;

import java.util.List;

public class CreateCourseInput {
    private String course_name;
    private String course_description;
    private String creator_id;
    private String chat_id;
    private String picture_id;
    private List<String> categories;
    
    public CreateCourseInput(String course_name, String course_description, String creator_id, String chat_id, String picture_id,
        List<String> categories) {
        this.course_name = course_name;
        this.course_description = course_description;
        this.creator_id = creator_id;
        this.chat_id = chat_id;
        this.picture_id = picture_id;
        this.categories = categories;
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

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    
    
    
}
