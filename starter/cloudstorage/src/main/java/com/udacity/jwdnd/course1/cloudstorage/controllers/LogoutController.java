package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController implements LogoutSuccessHandler {

    @GetMapping
    public String getLogoutPage() {
        return "logout";
    }
}
