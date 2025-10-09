package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String getLogoutPage(RedirectAttributes redirectAttributes) {
        // Creates Connection between "getLogoutPage()" Method & code that Displays Logout Page Status inside "login.html" file
        redirectAttributes.addFlashAttribute("log_out_success_msg", true);

        // Takes User Back to Logout Page
        return "redirect:/logout";
    }
}
