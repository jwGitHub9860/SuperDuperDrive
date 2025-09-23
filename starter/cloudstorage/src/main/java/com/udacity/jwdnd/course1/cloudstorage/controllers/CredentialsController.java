package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class CredentialsController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    public CredentialsController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    public String enterCredentials(@RequestParam("chooseCredentials") MultipartFile webpageCredentials, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {}
}