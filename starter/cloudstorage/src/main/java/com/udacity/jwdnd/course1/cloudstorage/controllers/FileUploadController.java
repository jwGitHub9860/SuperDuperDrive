package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class FileUploadController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    public FileUploadController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    // Connects code with { id="fileUpload" } in "home.html" File to "uploadFile()" Method
    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {
        Users users = userService.getUser(authentication.getName());
        Files chosenFile = new Files(fileUpload.getOriginalFilename(), null, fileUpload.getContentType(), Long.toString(fileUpload.getSize()), fileUpload.getBytes(), users.getUserId());

        List<Files> allUploadedFiles = this.fileService.getAllFilesByUserId(chosenFile.getUserId());
        
        // Checks if File Uploaded Successfully
        try {
            // Checks if File is Duplicate or Empty
            for(Files fileItem : allUploadedFiles) {
                if(chosenFile.getFilename().equals(fileItem.getFilename())) {
                    redirectAttributes.addFlashAttribute("duplicate_message", true);
                }
                else if(fileUpload.getSize() <= 0) {
                    redirectAttributes.addFlashAttribute("empty_message", true);
                }
            }

            fileService.uploadFile(chosenFile);
            System.out.println("File Upload Successful!");
            
            model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
            model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
            model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));
            
            // Creates Connection between "uploadFile()" Method & code that Displays Status of Uploading File Successfully inside "home.html" file
            redirectAttributes.addFlashAttribute("upload_message_status", true);
        } catch (Exception e) {
            e.printStackTrace();
            // Creates Connection between "uploadFile()" Method & code that Displays Status of Failing to Upload File inside "home.html" file
            redirectAttributes.addFlashAttribute("upload_message_status", true);
        }
        
        // Takes User Back to Home Page
        return "redirect:/home";
    }

    // Connects "Delete" option in "home.html" File to "deleteFile()" Method
    @GetMapping("/files/delete/{fileId}")
    public void deleteFile(@PathVariable String fileName, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        fileService.deleteFile(fileName);

        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));

        // Creates Connection between "deleteFile()" Method & code that Displays Status of Deleting File Successfully inside "home.html" file
        redirectAttributes.addFlashAttribute("delete_file_status", true);
    }
}