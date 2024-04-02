package leare.apiGateway.models.DocumentModels.batch;

public class VideoInfo {
    private String FilePath;
    private String VideoId;
    private String FileName;
    private String FileType;
    private String UserId;
    private long Date;


    public VideoInfo(String FilePath, String VideoId, String FileName, String FileType, String UserId, long Date) {
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

    public long getDate() {
        return this.Date;
    }

    public void setDate(long Date) {
        this.Date = Date;
    }

}
