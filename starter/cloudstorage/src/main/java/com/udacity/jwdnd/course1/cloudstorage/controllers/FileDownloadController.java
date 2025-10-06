package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLConnection;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
public class FileDownloadController {
    private final FileService fileService;

    public FileDownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("files/download/{fileId}")
    public void downloadFile(@PathVariable(value = "fileId") Integer fileId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) throws FileNotFoundException {
        Files chosenDownloadFile = fileService.getFileByFileId(fileId);

        // Setting up File Path
        String chosenFilePath = System.getProperty("user.dir") + "/Uploads";
        File chosenFile = new File(chosenFilePath + "/Downloads/" + fileService.getFileByFileName(chosenDownloadFile.getFilename()));

        // Checks if Chosen File Exists
        if (chosenFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(chosenFile));
            
            // Obtains "chosenFile" Media Type (MIME type) for Content Type of "chosenFile"
            String mimeType = URLConnection.guessContentTypeFromName(chosenFile.getName());

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

            // Creates Connection between "downloadFile()" Method & code that Displays File Download Status inside "home.html" file
            redirectAttributes.addFlashAttribute("download_file_status", true);
        } else {
            redirectAttributes.addFlashAttribute("file_not_exist", true);
        }
    }
}