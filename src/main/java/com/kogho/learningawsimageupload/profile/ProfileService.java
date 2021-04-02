package com.kogho.learningawsimageupload.profile;

import com.kogho.learningawsimageupload.bucket.BucketName;
import com.kogho.learningawsimageupload.filestore.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProfileService {
    private final ProfileDataAccessService repository;
    private final FileStoreService fileStoreService;

    @Autowired
    public ProfileService(ProfileDataAccessService repository, FileStoreService fileStoreService) {
        this.repository = repository;
        this.fileStoreService = fileStoreService;
    }

    public List<Profile> getProfiles() {
        return repository.getProfiles();
    }

    public String uploadProfileImage(UUID id, MultipartFile file)  {
        if(file.isEmpty()) throw new IllegalStateException("Must specify a file!");
        if(!file.getContentType().startsWith("image")) throw new IllegalStateException("File is " + file.getContentType() + ", it has to be an image!");

        var user = repository.getProfile(id).orElseThrow(() -> new IllegalStateException("There is no user with id " + id));

//        var metadata = file. TODO
        String fileName = file.getName() + "_" + UUID.randomUUID();

        Map<String, String> metaMap = new HashMap<>();

        metaMap.put("name", file.getName());
        metaMap.put("originalName", file.getOriginalFilename());
        metaMap.put("description", file.getResource().getDescription());
        metaMap.put("type", file.getContentType());

        var metadata = Optional.of(metaMap);

        try {
            fileStoreService.save(BucketName.PROFILE_IMAGE.getBucket(), fileName, metadata, file.getInputStream());
        } catch(Exception e) {
            // oh well
            return "Failed to upload file: " + e.getMessage();
        }

        repository.setProfileImage(id, fileName);

        return "Succesffuly uploaded file!";
    }

    public byte[] downloadProfileImage(UUID id) throws IOException {
        var profile = repository.getProfile(id).orElseThrow(() -> new IllegalStateException("No profile with id " + id));

        String link = profile.getProfileImageLink().orElseThrow(() -> new IllegalStateException("This user has no profile image!"));

        return fileStoreService.retreive(BucketName.PROFILE_IMAGE.getBucket(), link);
    }
}
