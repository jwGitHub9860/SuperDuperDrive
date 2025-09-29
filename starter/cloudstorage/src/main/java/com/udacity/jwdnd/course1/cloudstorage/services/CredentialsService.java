package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public Credentials getCredentialsByCredentialId(Integer credentialId) {
        return credentialsMapper.getCredentialsByCredentialId(credentialId);
    }

    public Credentials getCredentialsByCredentialName(String credentialName) {
        return credentialsMapper.getCredentialsByCredentialName(credentialName);
    }

    public Credentials getCredentialsByUserId(Integer userId) {
        return credentialsMapper.getCredentialsByUserId(userId);
    }

    public int createCredentials(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.createCredentials(url, username, key, password, credentialId);
    }

    public Credentials editCredentials(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.editCredentials(url, username, key, password, credentialId);
    }

    public void deleteCredentials(String credentialName) {
        credentialsMapper.deleteCredentials(credentialName);
    }
}