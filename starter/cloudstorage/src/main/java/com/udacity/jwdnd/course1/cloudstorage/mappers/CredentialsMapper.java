package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM Credentials WHERE credentialsId = #{credentialsId}")
    Credentials getCredentialsByCredentialId(Integer credentialsId);

    @Select("SELECT * FROM Credentials WHERE credentialName = #{credentialName}")
    Credentials getCredentialsByCredentialName(String credentialName);

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    List<Credentials> getAllCredentialsByUserId(Integer userId);

    @Insert("INSERT INTO Credentials (url, username, key, password, credentialsId) VALUES(#{url}, #{username}, #{key}, #{password}, #{credentialsId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialsId")
    int createCredentials(String url, String username, String key, String password, Integer credentialsId);

    @Update("UPDATE Credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialsId = #{credentialsId}")
    Credentials editCredentials(String url, String username, String key, String password, Integer credentialsId);

    @Delete("DELETE FROM Credentials WHERE credentialsId = #{credentialsId}")
    void deleteCredentials(Integer credentialsId);
}