package com.udacity.jwdnd.course1.cloudstorage.controllers;

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
}