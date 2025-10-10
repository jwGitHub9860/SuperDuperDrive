package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/addNewNote")
    public String addNote(@RequestParam("noteTitle") String createNoteTitle, @RequestParam("noteId") Integer createNoteId, @RequestParam("noteDescription") String createNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        boolean isNoteNotDuplicate = true;
        Users users = userService.getUser(authentication.getName());

        // Checks if New Note is Duplicate
        for(Notes noteItem : noteService.getAllNotesByUserId(users.getUserId())) {
            if (createNoteTitle == noteItem.getNoteTitle()) {
                isNoteNotDuplicate = false;

                // Creates Connection between "addNote()" Method & code that Displays Note Addition Status inside "home.html" file
                redirectAttributes.addFlashAttribute("add_note_duplicate", true);
            }
        }
        
        if (isNoteNotDuplicate) {
            int isNoteCreated = noteService.createNote(createNoteTitle, createNoteDescription, users.getUserId());

            if (isNoteCreated == 1) {
                // Creates Connection between "addNote()" Method & code that Displays Note Addition Status inside "home.html" file
                redirectAttributes.addFlashAttribute("add_note_success", true);
            } else {
                // Creates Connection between "addNote()" Method & code that Displays Note Addition Status inside "home.html" file
                redirectAttributes.addFlashAttribute("add_note_fail", true);
            }
        }
        
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @PostMapping("/notes/edit/{noteId}")
    public String editNote(@RequestParam("noteTitle") String chosenNoteTitle, @RequestParam("noteId") Integer chosenNoteId, @RequestParam("noteDescription") String chosenNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        noteService.editNote(chosenNoteTitle, chosenNoteDescription, chosenNoteId);
        
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "editNote()" Method & code that Displays Status of Editing Note Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("edit_note_status", true);

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping("/notes/delete/{noteId}")
    public String deleteNote(@PathVariable(value = "noteId") Integer chosenNoteId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        noteService.deleteNote(chosenNoteId);

        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "deleteNote()" Method & code that Displays Status of Deleting Note Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("delete_note_status", true);

        // Takes User Back to Home Page
        return "redirect:/home";
    }
}