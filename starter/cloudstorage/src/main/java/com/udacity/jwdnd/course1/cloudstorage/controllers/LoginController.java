package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.controllers.FileService;
import com.udacity.jwdnd.course1.cloudstorage.controllers.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.controllers.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.controllers.UserService;

@Controller
public class LoginController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    public LoginController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginToWebsite(@RequestParam("userCredentials") MultipartFile loginCredentials, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {}
}