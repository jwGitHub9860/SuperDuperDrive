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
public class FileUploadController {
    private final FileService fileService;
    private final UserService userService;

    public FileUploadController(UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
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

    @RequestMapping("/files")
    public String[] getFiles() {
        String filePath = System.getProperty("user.dir") + "/Uploads";
        File directory = new File(filePath);
        String[] filenames = directory.list();
        return filenames;
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