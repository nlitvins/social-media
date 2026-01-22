package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.TestcontainersConfiguration;
import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    private CommentRepository sut;

    @Test
    void findAll() {
        List<Comment> all = sut.findAll();

        assertThat(all)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }
}