package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public Credentials getCredentialsByCredentialId(Integer credentialsId) {
        return credentialsMapper.getCredentialsByCredentialId(credentialsId);
    }

    public Credentials getCredentialsByCredentialName(String credentialName) {
        return credentialsMapper.getCredentialsByCredentialName(credentialName);
    }

    public List<Credentials> getAllCredentialsByUserId(Integer userId) {
        return credentialsMapper.getAllCredentialsByUserId(userId);
    }

    public int createCredentials(String url, String username, String key, String password, Integer credentialsId) {
        return credentialsMapper.createCredentials(url, username, key, password, credentialsId);
    }

    public Credentials editCredentials(String url, String username, String key, String password, Integer credentialsId) {
        return credentialsMapper.editCredentials(url, username, key, password, credentialsId);
    }

    public void deleteCredentials(Integer credentialsId) {
        credentialsMapper.deleteCredentials(credentialsId);
    }
}