package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;
    
    public HomeController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model, Authentication authentication) {
        UserService currentUserId = this.userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFilesForUser(currentUserId));
        model.addAttribute("notes", this.noteService.getNotesForUser(currentUserId));
        model.addAttribute("credentials", this.credentialsService.getCredentialsForUser(currentUserId));
        return "home";
    }
}