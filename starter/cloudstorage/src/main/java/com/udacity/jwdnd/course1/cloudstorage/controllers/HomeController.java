package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
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
        Users currentUserId = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(currentUserId.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(currentUserId.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(currentUserId.getUserId()));

        // Creates Connection between "getHomePage()" Method & code that Displays Home Page Status inside "home.html" file
        redirectAttributes.addFlashAttribute("displayHomePage", true);

        return "home";
    }
}