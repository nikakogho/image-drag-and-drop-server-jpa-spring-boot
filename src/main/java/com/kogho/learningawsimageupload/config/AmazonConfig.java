package com.kogho.learningawsimageupload.config;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Bean
    public AmazonS3 s3() {
        String accessKey = "AKIAQNMLDZPSPJ2BPXWS";
        String secretKey = "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";

        String region = "eu-central-1";

        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        var provider = new AWSStaticCredentialsProvider(credentials);

        return AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(provider).build();
    }
}
