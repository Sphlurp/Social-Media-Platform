package com.example.suyash_socialmedia.DTO;

public class UserResponseDTO {
    private int userID;
    private String name;

    public UserResponseDTO() {}

    public UserResponseDTO(int userID, String name) {
        this.userID = userID;
        this.name = name;
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
