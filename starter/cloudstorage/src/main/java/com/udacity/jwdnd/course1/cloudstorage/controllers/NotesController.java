package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NoteController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;

    public NoteController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    public NoteController addNote(@RequestParam("addNote") MultipartFile createNote, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {}
}