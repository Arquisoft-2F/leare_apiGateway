package leare.apiGateway.models.CoursesModels;

public class CreateSectionInput {
    private String module_id;
    private String section_name;
    private String section_content;
    private String video_id;
    private String[] files_array;
    private int pos_index;

    public CreateSectionInput(String module_id, String section_name, String section_content, String video_id,
            String[] files_array, int pos_index) {
        this.module_id = module_id;
        this.section_name = section_name;
        this.section_content = section_content;
        this.video_id = video_id;
        this.files_array = files_array;
        this.pos_index = pos_index;
    }
    
    public String getModule_id() {
        return module_id;
    }
    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }
    public String getSection_name() {
        return section_name;
    }
    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
    public String getSection_content() {
        return section_content;
    }
    public void setSection_content(String section_content) {
        this.section_content = section_content;
    }
    public String getVideo_id() {
        return video_id;
    }
    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }
    public String[] getFiles_array() {
        return files_array;
    }
    public void setFiles_array(String[] files_array) {
        this.files_array = files_array;
    }
    public int getPos_index() {
        return pos_index;
    }
    public void setPos_index(int pos_index) {
        this.pos_index = pos_index;
    }

    
}
