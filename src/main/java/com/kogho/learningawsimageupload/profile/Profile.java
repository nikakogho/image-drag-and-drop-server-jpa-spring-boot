package com.kogho.learningawsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Profile {
    private UUID id;
    private String username;
    private String profileImageLink;

    public Profile(UUID id, String username, String profileImageLink) {
        this.id = id;
        this.username = username;
        this.profileImageLink = profileImageLink;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getProfileImageLink() {
        return Optional.ofNullable(profileImageLink);
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, profileImageLink);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", profileImageLink='" + profileImageLink + '\'' +
                '}';
    }
}
