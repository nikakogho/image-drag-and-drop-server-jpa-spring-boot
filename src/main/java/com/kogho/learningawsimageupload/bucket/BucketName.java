package com.kogho.learningawsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("kogho-image-upload");

    private final String bucket;

    BucketName(String bucket) {
        this.bucket = bucket;
    }

    public  String getBucket() {
        return bucket;
    }
}
