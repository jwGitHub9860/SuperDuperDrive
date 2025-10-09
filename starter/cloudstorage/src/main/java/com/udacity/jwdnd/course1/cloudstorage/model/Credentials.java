package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private final String url;
    private final Integer credentialsId;
    private final String username;
    private final Integer userId;
    private final String key;
    private final String password;

    public Credentials(String url, Integer credentialsId, String username, Integer userId, String key, String password) {
        this.url = url;
        this.credentialsId = credentialsId;
        this.username = username;
        this.userId = userId;
        this.key = key;
        this.password = password;
    }

    public final String getUrl() { return url; }
    public final Integer getCredentialId() { return credentialsId; }
    public final String getUsername() { return username; }
    public final Integer getUserId() { return userId; }
    public final String getKey() { return key; }
    public final String getPassword() { return password; }

    /*public final String setUrl() { this.url = url; }
    public final Integer setCredentialId() { this.credentialsId = credentialsId; }
    public final String setUsername() { this.username = username; }
    public final Integer setUserId() { this.userId = userId; }
    public final String setKey() { this.key = key; }
    public final String setPassword() { this.password = password; }*/
}