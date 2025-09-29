package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NoteController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    public NoteController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    public String addNote(@RequestParam("addNote") String createNoteTitle, @RequestParam("addNote") Integer createNoteId, @RequestParam("addNote") String createNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        Notes newNote = new Notes(createNote.getName(), null, createNote.getContentType(), users.getUserId());
        
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("add_note_status", "Note added successfully!");

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    public String editNote(@RequestParam("chosenNote") String chosenNoteTitle, @RequestParam("chosenNote") Integer chosenNoteId, @RequestParam("chosenNote") String chosenNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        noteService.editNote(chosenNote.setFilename(noteService.getNoteTitle()), chosenNote.getNoteDescription(), users.getUserId());
        
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("edit_note_status", "Note edited successfully!");

        // Takes User Back to Home Page
        return "redirect:/home";
    }
}