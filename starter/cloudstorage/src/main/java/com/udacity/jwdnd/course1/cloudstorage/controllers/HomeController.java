import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

package com.udacity.jwdnd.course1.cloudstorage.controllers;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("files", this.fileService.getFilesForUser(currentUserId));
        model.addAttribute("notes", this.noteService.getNotesForUser(currentUserId));
        model.addAttribute("credentials", this.credentialService.getCredentialsForUser(currentUserId));
        return "home";
    }
}