package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface CredentialsMapper {
    Credentials getCredentialByCredentialId(Integer credentialId);

    Credentials getCredentialByCredentialName(String credentialName);
}