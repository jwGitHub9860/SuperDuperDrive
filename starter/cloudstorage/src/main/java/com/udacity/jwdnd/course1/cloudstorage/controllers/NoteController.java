package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.ArrayList;
import java.util.List;

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
    private final List<Notes> allNotes = new ArrayList<Notes>();

    public NoteController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/addNewNote")
    public String addNote(@RequestParam("addNote") String createNoteTitle, @RequestParam("addNote") Integer createNoteId, @RequestParam("addNote") String createNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        Notes newNote = new Notes(createNoteTitle, createNoteId, createNoteDescription, users.getUserId());

        // Checks if New Note is Duplicate
        try {
            for(Notes noteItem : allNotes) {
                if (createNoteTitle == noteItem.getNoteTitle()) {
                    throw new IllegalArgumentException("Note is duplicate!");
                }
            }
            
            noteService.createNote(createNoteTitle, createNoteDescription, users.getUserId());
            allNotes.add(newNote);

            // Creates Connection between "addNote()" Method & code that Displays Note Addition Status inside "home.html" file
            redirectAttributes.addFlashAttribute("add_note_not_duplicate", true);
        } catch (Exception e) {
            e.printStackTrace();
            // Creates Connection between "addNote()" Method & code that Displays Note Addition Status inside "home.html" file
            redirectAttributes.addFlashAttribute("add_note_not_duplicate", false);
        }
        
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @PostMapping("/notes/edit/{noteId}")
    public String editNote(@RequestParam("chosenNote") String chosenNoteTitle, @RequestParam("chosenNote") Integer chosenNoteId, @RequestParam("chosenNote") String chosenNoteDescription, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
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
    public void deleteNote(@PathVariable(value = "chosenNoteId") Integer chosenNoteId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        noteService.deleteNote(chosenNoteId);

        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "deleteNote()" Method & code that Displays Status of Deleting Note Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("delete_note_status", true);
    }
}