package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM Credentials WHERE credentialid = #{credentialId}")
    Credentials getCredentialByCredentialId(Integer credentialId);

    @Select("SELECT * FROM Credentials WHERE credentialName = #{credentialName}")
    Credentials getCredentialByCredentialName(String credentialName);

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    Credentials getCredentialByUserId(Integer userId);

    Credentials editCredential(String url, String username, String key, String password, Integer credentialId);

    void deleteCredential(String credentialName);
}