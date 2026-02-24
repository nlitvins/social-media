package com.nlitvins.social_media.outbound.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.net.URI;
import java.util.Optional;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(
            @Value("${aws.region}") String region,
            @Value("${aws.s3.endpoint:}") Optional<String> endpoint
    ) {

        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(region));

        endpoint.ifPresent(e ->
                builder.endpointOverride(URI.create(e))
        );

        return builder.build();
    }
}

