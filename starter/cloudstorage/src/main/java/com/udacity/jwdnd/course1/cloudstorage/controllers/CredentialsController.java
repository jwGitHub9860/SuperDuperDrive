package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class CredentialsController {
    private final EncryptionService encryptionService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;
    private final List<Credentials> allCredentials = new ArrayList<Credentials>();

    public CredentialsController(EncryptionService encryptionService, FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/addNewCredentials")
    public String addCredentials(@RequestParam("addCredentials") String addCredentialsUrl, @RequestParam("addCredentials") Integer addCredentialsId, @RequestParam("addCredentials") String addCredentialsUsername, @RequestParam("addCredentials") String addCredentialsPassword, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        // Encrypts Password Credentials
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encryptedCredentialsPassword = encryptionService.encryptValue(addCredentialsPassword, key);

        Users users = userService.getUser(authentication.getName());
        Credentials newCredentials = new Credentials(addCredentialsUrl, addCredentialsId, addCredentialsUsername, users.getUserId(), key, addCredentialsPassword);

        // Checks if New Credentials are Duplicate
        try {
            for(Credentials credentialsItem : allCredentials) {
                if (credentialsItem.getUsername() == addCredentialsUsername) {
                    throw new IllegalArgumentException("Credentials are duplicate!");
                }
            }
            credentialsService.createCredentials(addCredentialsUrl, addCredentialsUsername, key, addCredentialsPassword, users.getUserId());
            allCredentials.add(newCredentials);

            // Creates Connection between "addCredentials()" Method & code that Displays Status of Adding Credentials Successfully inside "home.html" file
            redirectAttributes.addFlashAttribute("add_credentials_not_duplicate", true);
        } catch (Exception e) {
            e.printStackTrace();

            // Creates Connection between "addCredentials()" Method & code that Displays Status of Duplicate Credentials inside "home.html" file
            redirectAttributes.addFlashAttribute("add_credentials_not_duplicate", false);
        }
        
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Take User Back to Home Page
        return "redirect:/home";
    }

    @PostMapping("/credentials/edit/{credentialsId}")
    public String editCredentials(@RequestParam("chosenCredentials") String chosenCredentialsUrl, @RequestParam("chosenCredentials") String chosenCredentialsUsername, @RequestParam("chosenCredentials") String chosenCredentialsKey, @RequestParam("chosenCredentials") String chosenCredentialsPassword, @RequestParam("chosenCredentials") Integer chosenCredentialsId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Users users = userService.getUser(authentication.getName());
        credentialsService.editCredentials(chosenCredentialsUrl, chosenCredentialsUsername, chosenCredentialsKey, chosenCredentialsPassword, users.getUserId());

        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "editCredentials()" Method & code that Displays Status of Editing Credentials Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("edit_credential_status", true);

        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @GetMapping("/credentials/delete/{credentialsId}")
    public String deleteCredentials(@PathVariable(value = "credentialsId") Integer chosenCredentialsId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        credentialsService.deleteCredentials(chosenCredentialsId);
        
        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "deleteCredentials()" Method & code that Displays Status of Deleting Credentials Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("delete_credential_status", true);

        // Takes User Back to Home Page
        return "redirect:/home";
    }
}