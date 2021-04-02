package com.kogho.learningawsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("profiles")
@CrossOrigin("*")
public class ProfileController {
    private final ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public List<Profile> getProfiles() {
        return service.getProfiles();
    }

    @PostMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadProfileImage(@PathVariable(value = "id") UUID id, @RequestParam(value = "file") MultipartFile file) {
        return service.uploadProfileImage(id, file);
    }

    @GetMapping(value="/{id}/download")
    public byte[] downloadProfileImage(@PathVariable(value="id") UUID id) {
        try {
            return service.downloadProfileImage(id);
        } catch (IOException e) {
            return null;
        }
    }
}
