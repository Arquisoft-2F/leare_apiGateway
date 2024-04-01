package leare.apiGateway.models.DocumentModels;

public class DocumentGetValue {
 
    private String FilePath;
    private String VideoId;
    private String FileName;
    private String FileType;
    private String UserId;
    private Long Date;


    public DocumentGetValue(String FilePath, String VideoId, String FileName, String FileType, String UserId, Long Date) {
        this.FilePath = FilePath;
        this.VideoId = VideoId;
        this.FileName = FileName;
        this.FileType = FileType;
        this.UserId = UserId;
        this.Date = Date;
    }

    public String getFilePath() {
        return this.FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public String getVideoId() {
        return this.VideoId;
    }

    public void setVideoId(String VideoId) {
        this.VideoId = VideoId;
    }

    public String getFileName() {
        return this.FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFileType() {
        return this.FileType;
    }

    public void setFileType(String FileType) {
        this.FileType = FileType;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Long getDate() {
        return this.Date;
    }

    public void setDate(Long Date) {
        this.Date = Date;
    }


}
