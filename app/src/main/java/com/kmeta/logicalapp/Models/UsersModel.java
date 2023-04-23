package com.kmeta.logicalapp.Models;

public class UsersModel {

    private Integer id;
    private String name;
    private String userName;
    private String email;

    public UsersModel(Integer id, String name, String userName, String email) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
    }

    public UsersModel(String name, String userName, String email) {
        this.name = name;
        this.userName = userName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
