package leare.apiGateway.models.DocumentModels.batch;

public class VideoInfo {
    private String filePath;
    private String videoId;
    private String fileName;
    private String fileType;
    private String userId;
    private long date;


    public VideoInfo(String filePath, String videoId, String fileName, String fileType, String userId, long date) {
        this.filePath = filePath;
        this.videoId = videoId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.userId = userId;
        this.date = date;
    }


    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }


}
