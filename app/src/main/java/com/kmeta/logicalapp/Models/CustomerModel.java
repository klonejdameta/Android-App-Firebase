package com.kmeta.logicalapp.Models;

public class CustomerModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String latitude;
    private String longitude;
    private String isActive;

    public CustomerModel(Integer id,
                         String firstName,
                         String lastName,
                         String birthDate,
                         String address,
                         String latitude,
                         String longitude,
                         String isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isActive = isActive;
    }

    public CustomerModel(String firstName,
                         String lastName,
                         String birthDate,
                         String address,
                         String latitude,
                         String longitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getIsActive() {
        return isActive;
    }
}
