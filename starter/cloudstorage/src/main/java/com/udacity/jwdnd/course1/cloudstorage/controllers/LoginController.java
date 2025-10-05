package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @GetMapping
    public String getLoginPage(RedirectAttributes redirectAttributes) {
        // Creates Connection between "getLoginPage()" Method & code that Displays Login Page Status inside "home.html" file
        redirectAttributes.addFlashAttribute("displayLoginPage", true);

        return "login";
    }
}