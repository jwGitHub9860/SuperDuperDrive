package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String getLogoutPage() {
        return "logout";
    }
}
