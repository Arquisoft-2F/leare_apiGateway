package leare.apiGateway.models.SearchModels;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post{
        private String id;
        private String name;
        private Optional<String> lastname;
        private Optional<String> nickname;
        private Optional<String> description;
        private Optional<String> picture;
        private String type;
        

        
        public Post() {
        }
        @JsonCreator
        public Post(@JsonProperty("id") String id,@JsonProperty("name") String name,@JsonProperty("lastname") String lastname,@JsonProperty("nickname") String nickname,
                @JsonProperty("description") String description,@JsonProperty("picture") String picture,@JsonProperty("type") String type) {
            this.id = id;
            this.name = name;
            this.lastname = Optional.ofNullable(lastname);
            this.nickname = Optional.ofNullable(nickname);
            this.description = Optional.ofNullable(description);
            this.picture = Optional.ofNullable(picture);
            this.type= type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        
    }
