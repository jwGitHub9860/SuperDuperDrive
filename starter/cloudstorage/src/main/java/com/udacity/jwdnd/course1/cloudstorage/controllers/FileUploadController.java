package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpSecurity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final Users users;

    public FileUploadController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication,) {
        // Sets up file path
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + fileUpload.getOriginalFilename();
        String fileUploadStatus;
        
        // Checks if File Uploaded Successfully
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            fout.write(fileUpload.getBytes());

            fout.close();
            fileUploadStatus = "File uploaded successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            fileUploadStatus = "File upload failed!";
        }
        Users users = this.userService.getUser(authentication.getName()).getUserId();
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));
        System.out.println(fileUploadStatus);
        return fileUploadStatus;
    }

    @RequestMapping("/files")
    public void deleteFile(@PathVariable String filename) {
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + filename;
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}