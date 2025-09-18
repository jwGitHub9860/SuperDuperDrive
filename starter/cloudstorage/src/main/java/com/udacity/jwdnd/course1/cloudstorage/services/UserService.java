import java.security.SecureRandom;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

package com.udacity.jwdnd.course1.cloudstorage.services;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        byte[] salt = new byte[16];
        User newUser = new User(null, user.getUsername(), encodedPassword, user.getFirstName(), user.getLastName());
        return userMapper.insert(newUser);
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}