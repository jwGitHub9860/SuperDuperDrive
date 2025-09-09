import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

package com.udacity.jwdnd.course1.cloudstorage.controllers;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String homeView() {
        return "home";
    }
}