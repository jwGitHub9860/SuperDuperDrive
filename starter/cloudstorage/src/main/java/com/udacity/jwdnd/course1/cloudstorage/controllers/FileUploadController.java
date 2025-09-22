package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpSecurity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class FileUploadController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;
    private List<Files> uploadedFiles = new ArrayList<Files>();

    public FileUploadController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Files chosenFile = new Files(fileUpload.getOriginalFileName(), null, fileUpload.getContentType(), Long.toString(fileUpload.getSize()), fileUpload.getBytes(), fileUpload.getUserId());
        
        // Checks if File Uploaded Successfully
        try {
            // Checks if File is Duplicate or Empty
            for(Files fileItem : uploadedFiles) {
                if(chosenFile.getFilename().equals(fileItem.getOriginalFileName())) {
                    redirectAttributes.addFlashAttribute("duplicate_message", "File has duplicate name!");
                }
                else if(chosenFile.getSize() <= 0) {
                    redirect.addFlashAttribute("empty_message", "File is empty!");
                }
            }

            uploadedFiles.add(chosenFile);
            
            Users users = this.userService.getUser(authentication.getName());
            model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
            
            redirectAttributes.addFlashAttribute("upload_message", "File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("upload_message", "File upload failed!");
        }
        
        // Takes User Back to Home Page
        return "redirect:/home";
    }

    @RequestMapping("/files")
    public void deleteFile(@PathVariable String filename, Authentication authentication, RedirectAttributes redirectAttributes) {
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + filename;
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }

        Users users = this.userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
    }
}