package com.kogho.learningawsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Repository
public class ProfileDataAccessService {
    private final ProfileDB db;

    @Autowired
    public ProfileDataAccessService(ProfileDB db) {
        this.db = db;
    }

    public List<Profile> getProfiles() {
        return db.getProfiles();
    }

    public Optional<Profile> getProfile(UUID id) {
        return db.getProfile(id);
    }

    public void setProfileImage(UUID id, String link) {
        var profile = getProfile(id).orElseThrow(() -> new IllegalStateException("There is no profile with id " + id));

        profile.setProfileImageLink(link);

        db.save(profile);
    }
}
