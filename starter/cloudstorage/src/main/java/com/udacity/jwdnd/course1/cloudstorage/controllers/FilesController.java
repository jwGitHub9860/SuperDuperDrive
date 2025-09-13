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

package com.udacity.jwdnd.course1.cloudstorage.controllers;

@Controller
@RequestMapping("/files")

public class FilesController {
    private final UserService userService;

    public FilesController(UserService userService) {
        this.userService = userService;
    }

    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload) {
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
        return fileUploadStatus;
    }

    public String[] getFiles() {
        String filePath = System.getProperty("user.dir") + "/Uploads";
        File directory = new File(filePath);
        String[] filenames = directory.list();
        return filenames;
    }

    public ResponseEntity downloadFile(@PathVariable String filename) throws FileNotFoundException {
        // Checks if file exists
        String fileUploadPath = System.getProperty("user.dir") + "/Uploads";
        String[] filenames = getFiles();
        boolean fileExists = Arrays.asList(filenames).contains(filename);
    }
}