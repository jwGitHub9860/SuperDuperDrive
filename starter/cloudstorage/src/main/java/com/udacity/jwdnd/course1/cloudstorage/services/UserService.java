package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;

@Service
public class UserService {
    private final HashService hashService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(HashService hashService, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        String encodedSalt = userMapper.getUser(username).getSalt();
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);
        return hashedPassword.equals(userMapper.getUser(username).getPassword());
    }

    public int createUser(Users user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        Users newUser = new Users(user.getUserId(), user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName());
        return userMapper.insert(newUser);
    }

    public Users getUser(String username) {
        return userMapper.getUser(username);
    }
}