package com.example.suyash_socialmedia.DTO;

import com.example.suyash_socialmedia.model.userp.User1;

public class CommentCreator {

        private int userID;
        private String name;

        public CommentCreator(User1 user) {
            this.userID = user.getId();
            this.name = user.getName();
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

}
