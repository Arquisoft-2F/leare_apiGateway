package leare.apiGateway.models.SearchModels;

import java.util.Optional;

public class Highlight{
        private String[] name;
        private String[] lastname;
        private String[] nickname;
        private String[] description;
        
        

        public Highlight() {
        }



        public Highlight(String[] name, String[] lastname, String[] nickname, String[] description) {
            this.name = name;
            this.lastname = lastname;
            this.nickname = nickname;
            this.description = description;
        }



        public String[] getName() {
            return name;
        }



        public void setName(String[] name) {
            this.name = name;
        }



        public String[] getLastname() {
            return lastname;
        }



        public void setLastname(String[] lastname) {
            this.lastname = lastname;
        }



        public String[] getNickname() {
            return nickname;
        }



        public void setNickname(String[] nickname) {
            this.nickname = nickname;
        }



        public String[] getDescription() {
            return description;
        }



        public void setDescription(String[] description) {
            this.description = description;
        }



        

        
    }
