package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM Credentials WHERE credentialid = #{credentialId}")
    Credentials getCredentialsByCredentialId(Integer credentialId);

    @Select("SELECT * FROM Credentials WHERE credentialName = #{credentialName}")
    Credentials getCredentialsByCredentialName(String credentialName);

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    Credentials getCredentialsByUserId(Integer userId);

    @Insert("INSERT INTO Credentials (url, username, key, password, credentialId) VALUES(#{url}, #{username}, #{key}, #{password}, #{credentialId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredentials(String url, String username, String key, String password, Integer credentialId);

    @Update("UPDATE Credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
    Credentials editCredential(String url, String username, String key, String password, Integer credentialId);

    @Delete("DELETE FROM Credentials WHERE credentialId = #{credentialId}")
    void deleteCredential(String credentialName);
}