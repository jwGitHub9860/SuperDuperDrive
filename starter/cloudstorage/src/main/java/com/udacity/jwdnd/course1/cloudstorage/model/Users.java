package com.udacity.jwdnd.course1.cloudstorage.model;

public class Users {
    private final Integer userId;
    private final String username;
    private final String salt;
    private final String password;
    private final String firstName;
    private final String lastName;

    public Users(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public final Integer getUserId() { return userId; }
    public final String getUsername() { return username; }
    public final String getSalt() { return salt; }
    public final String getPassword() { return password; }
    public final String getFirstName() { return firstName; }
    public final String getLastName() { return lastName; }

    public final Integer setUserId() { this.userId = userId; }
    public final String setUsername() { this.username = username; }
    public final String setSalt() { this.salt = salt; }
    public final String setPassword() { this.password = password; }
    public final String setFirstName() { this.firstName = firstName; }
}