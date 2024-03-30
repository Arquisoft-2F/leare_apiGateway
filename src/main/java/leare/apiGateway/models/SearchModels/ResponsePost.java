package leare.apiGateway.models.SearchModels;

import graphql.com.google.common.base.Optional;

    public class ResponsePost{
        private Highlight highlight;
        private Post post;
        private String error;
        public ResponsePost() {
        }

        
        public ResponsePost(Highlight highlight, Post post, String error) {
            this.highlight = highlight;
            this.post = post;
            this.error = error;
        }


        public Highlight getHighlight() {
            return highlight;
        }
        public void setHighlight(Highlight highlight) {
            this.highlight = highlight;
        }
        public Post getPost() {
            return post;
        }
        public void setPost(Post post) {
            this.post = post;
        }


        public String getError() {
            return error;
        }


        public void setError(String error) {
            this.error = error;
        }
        
        
        

    }
