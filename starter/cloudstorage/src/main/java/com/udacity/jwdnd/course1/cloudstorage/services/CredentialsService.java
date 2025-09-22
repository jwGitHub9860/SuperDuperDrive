package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;

@Service
public class CredentialService {
    private final CredentialsMapper credentialsMapper;

    public CredentialsMapper(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }
}