package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    public CredentialsController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @GetMapping
    public String addCredentials(@RequestParam("addCredentials") String addCredentialsUrl, @RequestParam("addCredentials") String addCredentialsId, @RequestParam("addCredentials") String addCredentialsUsername, @RequestParam("addCredentials") String addCredentialsKey, @RequestParam("addCredentials") String addCredentialsPassword, @RequestParam("addCredentials") String addCredentialsDecryptedPassword, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        credentialsService.createCredentials(webpageCredentials.getUrl(), webpageCredentials.getUsername(), webpageCredentials.getKey(), webpageCredentials.getPassword(), webpageCredentials.getCredentialId());
        
        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("credentials_status", "Credentials added successfully!");

        // Take User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping
    public String editCredentials(@RequestParam("chooseCredential") MultipartFile chosenCredential, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        credentialsService.editCredentials(chosenCredential.getUrl(), chosenCredential.getUsername(), chosenCredential.getKey(), chosenCredential.getPassword(), chosenCredential.getCredentialId());

        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("edit_credential_status", "Credential edited successfully!");

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping
    public void deleteCredentials(@RequestParam("chooseCredential") MultipartFile chosenCredential, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        credentialsService.deleteCredentials(chosenCredential.getCredentialId());
        
        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getNoteByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("delete_credential_status", "Credential delete successful!");
    }
}