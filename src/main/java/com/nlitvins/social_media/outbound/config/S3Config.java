package com.nlitvins.social_media.outbound.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(
            @Value("${aws.region}") String region,
            @Value("${aws.s3.access-key:}") String accessKey,
            @Value("${aws.s3.secret-key:}") String secretKey,
            @Value("${aws.s3.endpoint}") String endpoint
    ) {

        AwsBasicCredentials awsBasicCredentials =
                AwsBasicCredentials.create(accessKey, secretKey);

        S3ClientBuilder builder = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build());

        return builder.build();
    }
}

