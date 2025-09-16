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
public class FileDownloadController {
    private final FileService fileService;

    public FileDownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity downloadFile(@PathVariable String filename) throws FileNotFoundException {
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
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}