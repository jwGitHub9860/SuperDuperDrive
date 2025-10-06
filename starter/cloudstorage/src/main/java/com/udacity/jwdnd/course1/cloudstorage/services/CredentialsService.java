package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public Credentials getCredentialsByCredentialId(Integer credentialId) {
        return credentialsMapper.getCredentialsByCredentialId(credentialId);
    }

    public Credentials getCredentialsByCredentialName(String credentialName) {
        return credentialsMapper.getCredentialsByCredentialName(credentialName);
    }

    public List<Credentials> getAllCredentialsByUserId(Integer userId) {
        return credentialsMapper.getAllCredentialsByUserId(userId);
    }

    public int createCredentials(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.createCredentials(url, username, key, password, credentialId);
    }

    public Credentials editCredentials(String url, String username, String key, String password, Integer credentialId) {
        return credentialsMapper.editCredentials(url, username, key, password, credentialId);
    }

    public void deleteCredentials(Integer credentialId) {
        credentialsMapper.deleteCredentials(credentialId);
    }

    public String encryptCredentials(String usernameCredential, String passwordCredential) {
        return encryptionService.encryptValue(usernameCredential, passwordCredential);
    }

    public String decryptCredentials(String usernameCredential, String passwordCredential) {
        return encryptionService.decryptValue(usernameCredential, passwordCredential);
    }
}