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

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class FileDownloadController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;

    public FileDownloadController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity downloadFile(@PathVariable String filename, Authentication authentication, RedirectAttributes redirectAttributes) throws FileNotFoundException {
        // Checks if file exists
        String fileUploadPath = System.getProperty("user.dir") + "/Uploads";
        String[] filenames = getFiles();
        boolean fileExists = Arrays.asList(filenames).contains(filename);
        if (!fileExists) {
            return ResponseEntity("File not found", HttpStatus.NOT_FOUND);
        }
        
        // Sets up file path where Downloaded File will be located
        String downloadFilePath = fileUploadPath + File.separator + filename;

        File file = new File(downloadFilePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        Users users = this.userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getFileByUserId(users.getUserId()));

        redirectAttributes.addFlashAttribute("message", "File downloaded successfully!");
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}