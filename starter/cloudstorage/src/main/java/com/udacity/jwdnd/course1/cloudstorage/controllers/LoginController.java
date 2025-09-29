package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;

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
    public String getLoginPage(@RequestParam("userCredentials") MultipartFile loginCredentials, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("displayLoginPage", true);
        return "login";
    }
}