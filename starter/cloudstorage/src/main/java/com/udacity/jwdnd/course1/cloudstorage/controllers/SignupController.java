package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
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

    // Do NOT Need to Specify "@PostMapping" Path because Other Method ONLY DISPLAYS Sign Up Page, NO OTHER FUNCTIONS & there's Only 2 Methods
    @PostMapping
    public String signUpForNewAccount(@RequestParam("inputFirstName") String firstName, @RequestParam("inputLastName") String lastname, @RequestParam("inputUsername") String username, @RequestParam("inputPassword") String password, Authentication authentication, RedirectAttributes redirectAttributes) {
        // Checks if "username" Already Exists
        if (userService.isUsernameAvailable(username)) {
            // Creates Connection between "signUpForNewAccount()" Method & code that Displays Logout Signup Error Message inside "signup.html" file
            redirectAttributes.addFlashAttribute("signup_error", false);

            // Creates "salt" for Creating New User
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Use Encoding when Converting "byte[]" to "String" -> Because Default Encoding will be Used & Can Be DIFFERENT on Different Machines
            String encodedSalt = Base64.getEncoder().encodeToString(salt);

            Users users = new Users(null, username, encodedSalt, password, firstName, lastname);
            int isUserCreated = userService.createUser(users);

            // Checks if New User was Created Correctly
            if (isUserCreated == 1) {
                redirectAttributes.addFlashAttribute("signup_successful", true);
            } else {
                redirectAttributes.addFlashAttribute("signup_error", true);
            }
        } else {
            // Creates Connection between "signUpForNewAccount()" Method & code that Displays Logout Signup Error Message inside "signup.html" file
            redirectAttributes.addFlashAttribute("signup_error", true);
        }

        // Takes User Back to Signup Page
        return "redirect:/signup";
    }
}