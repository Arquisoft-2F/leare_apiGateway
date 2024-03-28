package leare.apiGateway.models.CoursesModels;

public class CourseModule {
    private String module_id;
    private String module_name;
    private int pos_index;
    private ModuleSection[] sections;

    public CourseModule(String module_id, String module_name, int pos_index, ModuleSection[] sections) {
        this.module_id = module_id;
        this.module_name = module_name;
        this.pos_index = pos_index;
        this.sections = sections;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public int getPos_index() {
        return pos_index;
    }

    public void setPos_index(int pos_index) {
        this.pos_index = pos_index;
    }

    public ModuleSection[] getSections() {
        return sections;
    }

    public void setSections(ModuleSection[] sections) {
        this.sections = sections;
    }

    
}
