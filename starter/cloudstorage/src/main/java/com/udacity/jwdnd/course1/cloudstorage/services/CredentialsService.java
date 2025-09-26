package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.web.bind.annotation.*;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;

    public CredentialsMapper(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public Credentials getCredentialByCredentialId(Integer credentialId) {
        return credentialsMapper.getCredentialByCredentialId(credentialId);
    }

    public Credentials getCredentialByCredentialName(String credentialName) {
        return credentialsMapper.getCredentialByCredentialName(credentialName);
    }

    public Credentials getCredentialByUserId(Integer userId) {
        return credentialsMapper.getCredentialByUserId(userId);
    }

    public int createCredential(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.createCredential(url, username, key, password, credentialId);
    }

    public Credentials editCredential(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.editCredential(url, username, key, password, credentialId);
    }

    public void deleteCredential(String credentialName) {
        credentialsMapper.deleteCredential(credentialName);
    }
}