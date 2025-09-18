package com.udacity.jwdnd.course1.cloudstorage.services;

@Service

public class AuthenticationService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
}