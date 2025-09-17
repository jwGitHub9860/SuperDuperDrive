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
}