package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public int createUser(Users user) {
        SecureRandom random = new SecureRandom();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
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