package leare.apiGateway.models.CoursesModels;

public class CreateModuleInput {
    private String module_name;
    private String course_id;
    private int pos_index;
    
    public CreateModuleInput(String module_name, String course_id, int pos_index) {
        this.module_name = module_name;
        this.course_id = course_id;
        this.pos_index = pos_index;
    }
    public String getModule_name() {
        return module_name;
    }
    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
    public String getCourse_id() {
        return course_id;
    }
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    public int getPos_index() {
        return pos_index;
    }
    public void setPos_index(int pos_index) {
        this.pos_index = pos_index;
    }

    
}
