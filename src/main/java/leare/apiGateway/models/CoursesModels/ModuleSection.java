package leare.apiGateway.models.CoursesModels;

import java.util.List;

public class ModuleSection {
    private String section_id;
    private String section_name;
    private String section_content;
    private String video_id;
    private List<String> files_array;
    private int pos_index;

    public ModuleSection(String section_id, String section_name, String section_content, String video_id, List<String> files_array, int pos_index) {
        this.section_id = section_id;
        this.section_name = section_name;
        this.section_content = section_content;
        this.video_id = video_id;
        this.files_array = files_array;
        this.pos_index = pos_index;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
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

    public List<String> getFiles_array() {
        return files_array;
    }

    public void setFiles_array(List<String> files_array) {
        this.files_array = files_array;
    }

    public int getPos_index() {
        return pos_index;
    }

    public void setPos_index(int pos_index) {
        this.pos_index = pos_index;
    }

    

}
