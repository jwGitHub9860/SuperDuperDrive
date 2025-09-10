import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

package com.udacity.jwdnd.course1.cloudstorage.controllers;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String homeView() {
        return "home";
    }
}