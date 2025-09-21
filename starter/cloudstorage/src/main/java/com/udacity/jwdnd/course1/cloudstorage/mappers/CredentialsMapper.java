package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface CredentialsMapper {
    Credentials getCredentialByCredentialId(Integer credentialId);

    Credentials getCredentialByCredentialName(String credentialName);

    Credentials getCredentialByUserId(Integer userId);

    Credentials editCredential(String url, String username, String key, String password, Integer credentialId);
}