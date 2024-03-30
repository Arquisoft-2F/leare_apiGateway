package leare.apiGateway.models.UserModels;

public class Users extends UsersInput{
    private String id;
    public Users(String nickname, String name, String lastname, String picture_id, String nationality, String email,
            String web_site, String biography, String twitter_link, String linkedin_link, String facebook_link,
            String created_at, String updates_at, String id) {
        super(nickname, name, lastname, picture_id, nationality, email, web_site, biography, twitter_link,
                linkedin_link, facebook_link, created_at, updates_at);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}