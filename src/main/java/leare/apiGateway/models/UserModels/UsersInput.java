package leare.apiGateway.models.UserModels;

import leare.apiGateway.models.ObjectWhitPicture;

public class UsersInput implements ObjectWhitPicture{
    private String nickname;
    private String name;
    private String lastname;
    private String picture_id;
    private String nationality;
    private String email;
    private String web_site;
    private String biography;
    private String twitter_link;
    private String linkedin_link;
    private String facebook_link;
    private String created_at;
    private String updates_at;

    // Constructor, getters, and setters
    

    public String getNickname() {
        return nickname;
    }

    public UsersInput(String nickname, String name, String lastname, String picture_id, String nationality,
            String email, String web_site, String biography, String twitter_link, String linkedin_link,
            String facebook_link, String created_at, String updates_at) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.picture_id = picture_id;
        this.nationality = nationality;
        this.email = email;
        this.web_site = web_site;
        this.biography = biography;
        this.twitter_link = twitter_link;
        this.linkedin_link = linkedin_link;
        this.facebook_link = facebook_link;
        this.created_at = created_at;
        this.updates_at = updates_at;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb_site() {
        return web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getTwitter_link() {
        return twitter_link;
    }

    public void setTwitter_link(String twitter_link) {
        this.twitter_link = twitter_link;
    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public void setLinkedin_link(String linkedin_link) {
        this.linkedin_link = linkedin_link;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
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