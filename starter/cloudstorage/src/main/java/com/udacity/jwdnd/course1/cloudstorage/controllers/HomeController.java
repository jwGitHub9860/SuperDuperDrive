package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.authentication.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;
    
    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model, Authentication authentication) {
        UserService currentUserId = this.userService.getUser(authentication.getName()).getUserId();
        model.addAttribute("files", this.fileService.getFilesForUser(currentUserId));
        model.addAttribute("notes", this.noteService.getNotesForUser(currentUserId));
        model.addAttribute("credentials", this.credentialService.getCredentialsForUser(currentUserId));
        return "home";
    }
}