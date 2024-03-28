package leare.apiGateway.models.CoursesModels;

public class EditModuleInput {
    private String module_id;
    private String module_name;
    private int pos_index;
    
    public EditModuleInput(String module_id, String module_name, int pos_index) {
        this.module_id = module_id;
        this.module_name = module_name;
        this.pos_index = pos_index;
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

    
}
