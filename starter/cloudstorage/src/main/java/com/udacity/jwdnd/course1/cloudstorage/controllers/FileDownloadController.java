package com.udacity.jwdnd.course1.cloudstorage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
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
    public void downloadFile(@PathVariable(value = "fileId") Integer fileId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        Files chosenDownloadFile = fileService.getFileByFileId(fileId);

        // Setting up File Path
        String chosenFilePath = System.getProperty("user.dir") + "/Uploads";
        File chosenFile = new File(chosenFilePath + "/Downloads/" + chosenDownloadFile.getFilename());

        // Checks if Chosen File Exists
        if (chosenFile.exists()) {
            // Obtains "chosenFile" Media Type (MIME type) for Content Type of "chosenFile"
            String mimeType = URLConnection.guessContentTypeFromName(chosenFile.getName());

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            
            response.setContentType(chosenFilePath);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + chosenFile.getName() + "\""));
            response.setContentLength((int) chosenFile.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(chosenFile));
            
            // Copies Input Stream to "response" Output Stream & { "response.getOutputStream()" - gets reference of "ServletOutputStream" }
            FileCopyUtils.copy(inputStream, response.getOutputStream());

            // Creates Connection between "downloadFile()" Method & code that Displays File Download Status inside "home.html" file
            redirectAttributes.addFlashAttribute("download_file_status", true);
        } else {
            redirectAttributes.addFlashAttribute("file_not_exist", true);
        }
    }
}