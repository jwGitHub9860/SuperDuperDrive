package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class CredentialsController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;
    private final List<Credentials> allCredentials = new ArrayList<Credentials>();

    public CredentialsController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @GetMapping
    public String addCredentials(@RequestParam("addCredentials") String addCredentialsUrl, @RequestParam("addCredentials") Integer addCredentialsId, @RequestParam("addCredentials") String addCredentialsUsername, @RequestParam("addCredentials") String addCredentialsKey, @RequestParam("addCredentials") String addCredentialsPassword, @RequestParam("addCredentials") String addCredentialsDecryptedPassword, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());

        // Checks if New Credentials are Duplicate
        try {
            for(Credentials credentialsItem : allCredentials) {
                if (credentialsItem.getUsername() == addCredentialsUsername) {
                    throw new IllegalArgumentException("Credentials are duplicate!");
                }
            }
            credentialsService.createCredentials(addCredentialsUrl, addCredentialsUsername, addCredentialsKey, addCredentialsPassword, users.getUserId());
            allCredentials.add(newCredentials);
            redirectAttributes.addFlashAttribute("add_credentials_not_duplicate", true);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("add_credentials_not_duplicate", false);
        }
        
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Take User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping
    public String editCredentials(@RequestParam("chosenCredentials") String chosenCredentialsUrl, @RequestParam("chosenCredentials") String chosenCredentialsUsername, @RequestParam("chosenCredentials") String chosenCredentialsKey, @RequestParam("chosenCredentials") String chosenCredentialsPassword, @RequestParam("chosenCredentials") Integer chosenCredentialsId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        credentialsService.editCredentials(chosenCredentialsUrl, chosenCredentialsUsername, chosenCredentialsKey, chosenCredentialsPassword, users.getUserId());

        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("edit_credential_status", "Credential edited successfully!");

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping
    public void deleteCredentials(@RequestParam("chosenCredentials") String chosenCredentialName, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        credentialsService.deleteCredentials(chosenCredentialName);
        
        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("delete_credential_status", "Credential delete successful!");
    }
}