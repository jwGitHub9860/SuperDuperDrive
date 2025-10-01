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
    private List<Files> uploadedFiles = new ArrayList<Files>();

    public FileUploadController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {
        Users users = userService.getUser(authentication.getName());
        Files chosenFile = new Files(fileUpload.getOriginalFilename(), null, fileUpload.getContentType(), Long.toString(fileUpload.getSize()), fileUpload.getBytes(), users.getUserId());
        
        // Checks if File Uploaded Successfully
        try {
            // Checks if File is Duplicate or Empty
            for(Files fileItem : uploadedFiles) {
                if(chosenFile.getFilename().equals(fileItem.getFilename())) {
                    redirectAttributes.addFlashAttribute("duplicate_message", "File has duplicate name!");
                }
                else if(fileUpload.getSize() <= 0) {
                    redirectAttributes.addFlashAttribute("empty_message", "File is empty!");
                }
            }

            fileService.uploadFile(chosenFile);
            uploadedFiles.add(chosenFile);
            System.out.println("File Upload Successful!");
            
            model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
            model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
            model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));            
            redirectAttributes.addFlashAttribute("upload_message", "File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("upload_message", "File upload failed!");
        }
        
        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @RequestMapping("/files")
    public void deleteFile(@PathVariable String filename, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        fileService.deleteFile(filename);

        Users users = userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(users.getUserId()));
        model.addAttribute("credentials", this.credentialsService.getAllCredentialsByUserId(users.getUserId()));
        redirectAttributes.addFlashAttribute("delete_file_status", "File deleted successful!");
    }
}