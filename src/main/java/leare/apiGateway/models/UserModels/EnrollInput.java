package leare.apiGateway.models.UserModels;

public class EnrollInput{

    private float progress;
    private float score;
    private String listed_on;
    private String last_seen;
    private String created_at;
    private String updates_at;

    
    public EnrollInput() {
    }
    
    public EnrollInput(float progress, float score, String listed_on, String last_seen, String created_at,
            String updates_at) {
        this.progress = progress;
        this.score = score;
        this.listed_on = listed_on;
        this.last_seen = last_seen;
        this.created_at = created_at;
        this.updates_at = updates_at;
    }
    public float getProgress() {
        return progress;
    }
    public void setProgress(float progress) {
        this.progress = progress;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public String getListed_on() {
        return listed_on;
    }
    public void setListed_on(String listed_on) {
        this.listed_on = listed_on;
    }
    public String getLast_seen() {
        return last_seen;
    }
    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getUpdates_at() {
        return updates_at;
    }
    public void setUpdates_at(String updates_at) {
        this.updates_at = updates_at;
    }

    

}
