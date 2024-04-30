package com.example.suyash_socialmedia.model.userp;

import jakarta.persistence.*;

@Entity
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

   // @NotBlank(message = "Email is required")
    private String email;

   // @NotBlank(message = "Name is required")
    private String name;

   // @NotBlank(message = "Password is required")
    private String password;

    // Getters and setters
    // Constructors
    public User1() {}

    public User1(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return userID;
    }

    public void setId(int id) {
        this.userID = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
