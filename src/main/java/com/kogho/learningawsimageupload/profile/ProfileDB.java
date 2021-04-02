package com.kogho.learningawsimageupload.profile;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileDB {
    public List<Profile> getProfiles();
    public Optional<Profile> getProfile(UUID id);
    void save(Profile profile);
}
