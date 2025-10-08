package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    
    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @Bean
    public UserDetailsService signUp() {
        UserDetails userDetails = User.withUsername("user")
                                        .password("password")
                                        .roles("USER")
                                        .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}