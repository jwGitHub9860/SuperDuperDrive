package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/credentials/{username}")
    public String doesUsernameExist(String username, RedirectAttributes redirectAttributes) {
        if (userService.isUsernameAvailable(username)) {
            redirectAttributes.addFlashAttribute("signup_error_msg", false);
        } else {
            redirectAttributes.addFlashAttribute("signup_error_msg", true);
        }

        // Takes User Back to Signup Page
        return "redirect:/signup";
    }
}