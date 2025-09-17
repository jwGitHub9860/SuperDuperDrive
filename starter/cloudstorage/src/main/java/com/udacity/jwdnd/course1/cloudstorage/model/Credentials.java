package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private final String url;
    private final Integer credentialId;
    private final String username;
    private final Integer userId;
    private final String key;
    private final String password;
    private final String decryptedPassword;

    public Credentials(String url, Integer credentialId, String username, Integer userId, String key, String password, String decryptedPassword) {
        this.url = url;
        this.credentialId = credentialId;
        this.username = username;
        this.userId = userId;
        this.key = key;
        this.password = password;
        this.decryptedPassword = decryptedPassword;
    }

    public String getUrl() { return url; }
    public Integer getCredentialId() { return credentialId; }
    public String getUsername() { return username; }
    public Integer getUserId() { return userId; }
    public String getKey() { return key; }
    public String getPassword() { return password; }
    public String getDecryptedPassword() { return decryptedPassword; }

    public String setUrl() { this.url = url; }
    public Integer setCredentialId() { this.credentialId = credentialId; }
    public final String setUsername() { this.username = username; }
    public final Integer setUserId() { this.userId = userId; }
    public final String setKey() { this.key = key; }
    public final String setPassword() { this.password = password; }
    public final String setDecryptedPassword() { this.decryptedPassword = decryptedPassword; }
}