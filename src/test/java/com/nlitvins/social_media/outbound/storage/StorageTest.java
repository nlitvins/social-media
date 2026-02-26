package com.nlitvins.social_media.outbound.storage;

import com.nlitvins.social_media.outbound.config.S3Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StorageTest {

    @Autowired
    S3Config s3Config;

    @Autowired
    FileStorage fileStorage;


    @Test
    public void testStorage() {
        var file = new File("Test.txt");

        fileStorage.upload("1", file.toPath());


        assertThat(file).exists();
    }
}
