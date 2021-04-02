package com.kogho.learningawsimageupload.profile.fake;

import com.kogho.learningawsimageupload.profile.Profile;
import com.kogho.learningawsimageupload.profile.ProfileDB;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeProfileDB implements ProfileDB {
    private static final List<Profile> profiles = new ArrayList<>();

    static {
        profiles.add(new Profile(UUID.randomUUID(), "grisha", null));
        profiles.add(new Profile(UUID.randomUUID(), "Orochimaru", null));
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public Optional<Profile> getProfile(UUID id) {
        for(var profile : profiles) if(profile.getId().equals(id)) return Optional.of(profile);

        return Optional.empty();
    }

    @Override
    public void save(Profile profile) {
        for(var p : profiles) {
            if(p.getId().equals(profile.getId())) {
                p.setUsername(profile.getUsername());
                p.setProfileImageLink(profile.getProfileImageLink().orElse(null));
                return;
            }
        }

        profiles.add(profile);
    }
}
