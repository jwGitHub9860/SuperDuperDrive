package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String getLogoutPage(RedirectAttributes redirectAttributes) {
        // Creates Connection between "getLogoutPage()" Method & code that Displays Logout Page Status inside "login.html" file
        redirectAttributes.addFlashAttribute("log_out_success_msg", true);

        // Take User Back to Logout Page
        return "redirect:/logout";
    }
}
