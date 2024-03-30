package leare.apiGateway.models.SearchModels;

import graphql.com.google.common.base.Optional;

public class ResponsePost {

    public class Post{
        private String id;
        private String name;
        private Optional<String> lastname;
        private Optional<String> nickname;
        private Optional<String> description;
        private Optional<String> picture;
        
        public Post(String id, String name, Optional<String> lastname, Optional<String> nickname,
                Optional<String> description, Optional<String> picture) {
            this.id = id;
            this.name = name;
            this.lastname = lastname;
            this.nickname = nickname;
            this.description = description;
            this.picture = picture;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getLastname() {
            return lastname;
        }

        public void setLastname(Optional<String> lastname) {
            this.lastname = lastname;
        }

        public Optional<String> getNickname() {
            return nickname;
        }

        public void setNickname(Optional<String> nickname) {
            this.nickname = nickname;
        }

        public Optional<String> getDescription() {
            return description;
        }

        public void setDescription(Optional<String> description) {
            this.description = description;
        }

        public Optional<String> getPicture() {
            return picture;
        }

        public void setPicture(Optional<String> picture) {
            this.picture = picture;
        }
    }

    private Optional<Post> highlight; 
    private Optional<Post> post;
    public ResponsePost(Optional<Post> highlight, Optional<Post> post) {
        this.highlight = highlight;
        this.post = post;
    }
    public Optional<Post> getHighlight() {
        return highlight;
    }
    public void setHighlight(Optional<Post> highlight) {
        this.highlight = highlight;
    }
    public Optional<Post> getPost() {
        return post;
    }
    public void setPost(Optional<Post> post) {
        this.post = post;
    } 

    
    
}
