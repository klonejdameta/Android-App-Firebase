package com.kmeta.logicalapp.Models;

public class UsersModel {

    private String id;
    private String name;
    private String username;
    private String email;

    public UsersModel(String id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public UsersModel(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public UsersModel() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
