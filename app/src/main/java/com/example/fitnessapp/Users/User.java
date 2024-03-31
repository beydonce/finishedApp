package com.example.fitnessapp.Users;

public class User {
    private long id;
    private String email, name, password;
    private byte[] image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User(String email, String name, String password, byte[] image) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
    }

    public User(long id, String email, String name, String password, byte[] image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
    }
}
