package com.kogho.learningawsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FileStoreService {
    private final AmazonS3 s3;

    @Autowired
    public FileStoreService(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName, Optional<Map<String, String>> optionalMetadata, InputStream stream) {
        var metadata = new ObjectMetadata();

        optionalMetadata.ifPresent(map -> map.forEach(metadata::addUserMetadata));

        try {
            s3.putObject(path, fileName, stream, metadata);
        } catch(AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file", e);
        }
    }

    public byte[] retreive(String path, String fileName) throws IOException {
        var img = s3.getObject(path, fileName);

        return img.getObjectContent().readAllBytes();
    }
}
